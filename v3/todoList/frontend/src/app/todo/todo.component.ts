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
    this.todo = todo;
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
        this.notificationService.error('Não foi possível cadastrar o "To-Do"!');
      });
  }

  deleteTodo(todo: Todo) {
    this.service.delete(todo).subscribe(
      () => {
        this.todo = new Todo();
        this.notificationService.success('Todo removido com sucesso!');
        // this.todos.remove(todo); // FIXME
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
        this.notificationService.error('Não foi possível atualizar o "To-Do"!');
      }
    );
  }

  resetTodoForm(form: NgForm) {
    form.reset();
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
}
