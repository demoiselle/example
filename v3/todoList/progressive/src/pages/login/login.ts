import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { LoginService } from '../../providers/login-service';
import { RegisterPage } from '../register/register';
import { TodoListPage } from '../todo-list/todo-list';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  user: any = {
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
    console.log('TODO: handle form feedback.', form);
    this.login();
  }

  navToRegisterPage() {
    this.navCtrl.push(RegisterPage, {
    });
  }

  login() {
    try {
      this.loginService.signin({
        username: this.user.email,
        password: this.user.pass
      })
        .subscribe(
        res => {
          console.log('Signed-in:', res);
          // this.loginService.proceedToRedirect(['']);
          this.navCtrl.push(TodoListPage, {});
        },
        error => {
          if (error.status === 401 || error.status === 406) {
            let message = JSON.parse(error._body).error;
            // this.notificationService.error(message);
            console.log('Error:', message);
            this.user.pass = '';
          };
        });
    } catch (ex) {
      console.log('Exception:', ex);
    }
  }

}
