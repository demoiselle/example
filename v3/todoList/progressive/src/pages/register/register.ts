import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { LoginService } from '../../providers/login-service';
import { LoginPage } from '../login/login';
import { TodoListPage } from '../todo-list/todo-list';

@Component({
  selector: 'page-register',
  templateUrl: 'register.html'
})
export class RegisterPage {

  user: any = {
    name: 'Demoiselle',
    email: 'admin@demoiselle.org',
    pass: '12345678'
  };

  constructor(
    protected loginService: LoginService,
    public navCtrl: NavController,
    public navParams: NavParams) { }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  onSubmit($event, form) {
    this.register();
  }

  navToLoginPage() {
    this.navCtrl.push(LoginPage, {});
  }

  /**
   * Register and Sign-in a new user.
   */
  register() {
    this.loginService.register(this.user).subscribe(res => {
      // console.log('User created:', res);
      let newUser = res.json();
      this.loginService.signin({
        username: newUser.email,
        password: this.user.pass
      }).subscribe(res2 => {
        // console.log('User logged-in:', res2);
        this.navCtrl.push(TodoListPage, {});
      });
    });
  }
}
