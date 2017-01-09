import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { TodoService } from '../../providers/todo-service';
import { Todo } from '../../providers/todo-model';

@Component({
  selector: 'page-todo-form',
  templateUrl: 'todo-form.html'
})
export class TodoFormPage {

  todo: Todo;

  constructor(public todoService: TodoService, public navCtrl: NavController, public navParams: NavParams) {
    this.todo = new Todo();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TodoFormPage');
  }

  onSubmit($event, form){
    this.todoService.create(this.todo).subscribe(res => {
      console.log('To-Do created:', res);
      this.todo = new Todo();

      // go back to the TodoPage (list)
      this.navCtrl.pop();
    },
    error => {
      console.log('Error:', error);
    });
  }
}
