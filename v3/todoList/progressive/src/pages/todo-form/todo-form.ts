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
  minDate;
  maxDate;

  constructor(public todoService: TodoService, public navCtrl: NavController, public navParams: NavParams) {
    // min === data atual
    this.minDate = (new Date()).toISOString();
    // max === data atual + 1ano
    this.maxDate = new Date(new Date().getTime());
    this.maxDate.setFullYear(this.maxDate.getFullYear() + 1);
    this.maxDate = this.maxDate.toISOString();

    this.todo = navParams.get('todo') || new Todo();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TodoFormPage');
  }

  resetForm() {
    this.todo = new Todo();
  }

  onSubmit($event, form) {
    if (!this.todo.id) {
      this.todoService.create(this.todo).subscribe(res => {
        console.log('To-Do created:', res);

        this.resetForm();
        // go back to the TodoPage (list)
        this.navCtrl.pop();
      });
    } else {
      this.todoService.update(this.todo).subscribe(res => {
        console.log('To-Do updated:', res);
        // go back to the TodoPage (list)
        this.navCtrl.pop();
      });
    }
  }
}
