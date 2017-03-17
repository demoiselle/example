import { Component, OnInit, ViewChild, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PopoverDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { TodoService } from './shared/todo.service';
import { Todo } from './shared/todo.model';

import { SelectItem } from 'primeng/primeng';

@Component({
  selector: 'todo-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss']
})
export class TodoComponent implements OnInit {
  todo: Todo;
  todos: Todo[];

  @ViewChild('adiarTemplate') public adiarTemplate: PopoverDirective;

  public itemsPerPage: number = 2;
  public totalItems: number = 0;
  public currentPage: number = 1;

  date: Date;

  @Output()
  public selectedStatus;
  public status = [
    {label: 'loading...', value: 'loading...'}
  ];

  constructor(private service: TodoService, private notificationService: NotificationService) {
    this.initTodo();
  }

  initTodo() {
    this.todo = new Todo();
    this.todos = [];
    this.date = new Date();
    this.date.setHours(10);
    this.date.setMinutes(0);
    this.date.setSeconds(0);
  }

  ngOnInit() {
    this.list();
    this.loadStatus();
    this.selectedStatus = this.status[0].value;
  }

  loadStatus() {
    this.service.getStatus().subscribe(
      (result) => {
        this.status = [];
        Object.keys(result).forEach(element => {
          this.status.push({label: element, value: result[element]});
        });
        if (this.status.length > 0) {
          this.selectedStatus = this.status[0].value;
          this.todo.status = this.status[0].label;
        }
      },
      (error) => {
        console.log('error');
        console.log(error);
        this.notificationService.error('Não foi possível carregar a lista de status!');
      }
    );
  }

  onSubmit(form: NgForm) {
    this.todo.dateEnd = this.date.getTime().toString();
    if (!this.todo.id) {
      // Create a new todo
      this.addTodo(this.todo, form);
    } else {
      // Update todo values
      this.updateTodo(this.todo, form);
    }
  }

  onSelect(todo: Todo) {
    this.todo = this.cloneTodo(todo);
    this.date = new Date(this.todo.dateEnd);
    this.date.setDate(this.date.getDate()+1);
    this.date.setHours(10);
    this.date.setMinutes(0);
    this.date.setSeconds(0);
    if (this.todo.status) {
      this.status.forEach(element => {
        if (element.label == this.todo.status) {
          this.selectedStatus = element.value;
        }
      });
    }
  }

  onChangePage(event: any): void {
    this.currentPage = event.page;
    this.list();
  }

  list() {
    this.service.list(this.currentPage, this.itemsPerPage).subscribe(
      (result) => {
        this.todos = result.json().todos;
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      error => {
        this.notificationService.error('Não foi possível carregar a lista de "to-do\'s"!');
        this.totalItems = 20;
        this.todos = error;
      }
    );
  }

  addTodo(todo: Todo, form: NgForm) {
    this.service.create(todo).subscribe(
      (newTodo) => {
        delete newTodo.user;
        this.notificationService.success('Todo cadastrado com sucesso!');
        this.list();
        this.resetTodoForm(form);
      },
      error => {
        if (error.status == 400) {
          let errors = JSON.parse(error._body || []);
          for (let err of errors) {
            this.notificationService.error('Validação => Campo: ' + err.error + ' , Erro: ' + err.error_description);
          }
        } else {
          /* erros de validação (412) são tratandos utilizando o Observable de validação do serviço Http
            https://demoiselle.gitbooks.io/documentacao-frontend/content/validacao-com-demoiselle-3.html
          */
          this.notificationService.error('Não foi possível cadastrar o "To-Do"!');
        }

      });
  }

  deleteTodo(todo: Todo) {
    this.service.delete(todo).subscribe(
      () => {
        // remove todo from this.todos
        //let index = this.todos.map(function(item) { return item.id; }).indexOf(todo.id);
        //~index && this.todos.splice(index);
        this.list();

        this.todo = new Todo();
        this.notificationService.success('Todo removido com sucesso!');
      },
      error => {
        this.notificationService.error('Não foi possível remover o "To-Do"!');
      }
    );
  }

  updateTodo(todo: Todo, form: NgForm) {
    this.service.update(todo).subscribe(
      () => {
        this.notificationService.success('Todo atualizado com sucesso!');
        this.list();
        this.resetTodoForm(form);
      },
      error => {
        if (error.status == 412) {
          let errors = JSON.parse(error._body);
          for (let err of errors) {
            this.notificationService.error('Validação => Campo: ' + err.error + ' , Erro: ' + err.error_description);
          }
        } else {
          this.notificationService.error('Não foi possível atualizar o "To-Do"!');
        }
      }
    );
  }

  resetTodoForm(form: NgForm) {
    if (form) {
      form.reset();
    }
    this.todo = new Todo();
    this.date = new Date();
    this.date.setHours(10);
    this.date.setMinutes(0);
    this.date.setSeconds(0);
    this.selectedStatus = this.status[0].value;
  }

  adiar(todo, days) {
    // todo :)
    console.log('TODO: implementar funcionalidade de "adiar" - adiar %s dias.', days);
  }

  setStatus(todo, status) {
    // todo :)
    console.log('TODO: implementar funcionalidade de "setStatus" - novo status: %s', status);
  }

  cloneTodo(c: Todo): Todo {
    let copy = new Todo();
    for (let prop in c) {
      copy[prop] = c[prop];
    }
    return copy;
  }

  changeStatus(event) {
    this.status.forEach(element => {
      if (element.value == event.value) {
        this.selectedStatus = element.value;
        this.todo.status = element.label;
        console.log(this.todo.status);
      }
    });
  }
}
