import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PopoverDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { TodoService } from './shared/todo.service';
import { Todo } from './shared/todo.model';

@Component({
  selector: 'todo-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss']
})
export class TodoComponent implements OnInit {
  todo: Todo;
  todos: Todo[];

  @ViewChild('adiarTemplate') public adiarTemplate: PopoverDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: TodoService, private notificationService: NotificationService) {
    this.todo = new Todo();
    this.todos = [];
  }

  ngOnInit() {
    this.list();
    console.log('[TodoComponent] initialized.');
  }

  onSubmit(form: NgForm) {
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
  }

  onChangePage(event: any): void {
    this.currentPage = event.page;
    this.list();
  }

  list() {
    this.service.list(this.currentPage, this.itemsPerPage).subscribe(
      todos => {
        this.totalItems = 20; // backend must send  the total items for proper pagination config

        // Replace all content of array
        // http://stackoverflow.com/questions/23486687/short-way-to-replace-content-of-an-array
        [].splice.apply(this.todos, [0, this.todos.length].concat(todos));
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
        this.todos.push(newTodo);
        this.resetTodoForm(form);
      },
      error => {
        if (error.status == 412) {
          let errors = JSON.parse(error._body);
          for (let err of errors) {
            this.notificationService.error('Validação => Campo: ' + err.error + ' , Erro: ' + err.error_description);
          }
        } else if (error.status == 400) {
          let errors = JSON.parse(error._body || []);
          for (let err of errors) {
            this.notificationService.error('Validação => Campo: ' + err.error + ' , Erro: ' + err.error_description);
          }
        } else {
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
}
