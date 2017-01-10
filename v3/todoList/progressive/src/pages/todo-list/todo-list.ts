import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';

import { TodoService } from '../../providers/todo-service';
import { Todo } from '../../providers/todo-model';
import { TodoFormPage } from '../todo-form/todo-form';

@Component({
  selector: 'page-todo-list',
  templateUrl: 'todo-list.html'
})
export class TodoListPage {

  selectedTodo: Todo;
  todos: Todo[];
  showArchived: boolean;

  constructor(
    public todoService: TodoService,
    public navCtrl: NavController,
    public navParams: NavParams,
    public loadingCtrl: LoadingController) {
    // If we navigated to this page, we will have an item available as a nav param
    this.selectedTodo = navParams.get('todo');
    this.todos = [];
    this.showArchived = false;
    // this.list();
  }

  // ionViewDidLoad() {
  //   console.log('ionViewDidLoad TodoPage');
  // }

  // ionViewWillEnter() {
  //   console.log('ionViewWillEnter TodoPage');
  // }

  ionViewDidEnter() {
    console.log('ionViewDidEnter TodoPage');
    this.list();
  }

  list() {
    let currentPage = 0;
    let itemsPerPage = 10;

    let loading = this.loadingCtrl.create({
      content: 'Aguarde...'
    });
    loading.present();

    try {
      this.todoService.list(currentPage, itemsPerPage).subscribe(
        todos => {
          // limpa o array atual
          this.todos.length = 0;

          // Replace all content of array
          if (this.showArchived) {
            // http://stackoverflow.com/questions/23486687/short-way-to-replace-content-of-an-array
            [].push.apply(this.todos, todos);
          } else {
            // Adiciona os NÂO 'archived'
            todos.forEach(todo => {
              if (todo.status !== 'archived') {
                this.todos.push(todo);
              }
            });
          }

          loading.dismiss();
        },
        error => {
          console.error('Error', error);
          loading.dismiss();
          // this.notificationService.error('Não foi possível carregar a lista de "to-do\'s"!');
          // this.totalItems = 20;
          // this.todos = error;
        }
      );
    } catch (ex) {
      this.todos = [];
      loading.dismiss();
    }
  }

  itemTapped(event, todo) {
    this.navCtrl.push(TodoFormPage, {
      todo: todo
    });
  }

  defer(event, todo) {
    let days = 1;
    this.todoService.defer(todo, days)
      .subscribe(data => {
        // recarrega a lista
        this.list();
      });
  }

  archive(event, todo) {
    this.todoService.archive(todo)
      .subscribe(data => {
        // recarrega a lista
        this.list();
      });
  }

  openTodoFormPage() {
    this.navCtrl.push(TodoFormPage, {});
  }

  toggleVisibility() {
    // recarrega a lista
    this.list();
  }
}
