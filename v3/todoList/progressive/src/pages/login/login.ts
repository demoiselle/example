import { NavController, NavParams, LoadingController } from 'ionic-angular';
import { Component } from '@angular/core';

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
    public navParams: NavParams,
    public loadingCtrl: LoadingController) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  onSubmit($event, form) {

    // TODO: custom validations?
    if (form.valid) {
      this.login();
    }
  }

  navToRegisterPage() {
    this.navCtrl.push(RegisterPage, {
    });
  }

  login() {
    let loading = this.loadingCtrl.create({
      content: 'Autenticando...'
    });
    loading.present();

    try {
      this.loginService.signin({
        username: this.user.email,
        password: this.user.pass
      })
        .subscribe(
        res => {
          // console.log('Signed-in:', res);
          loading.dismiss();
          this.navCtrl.setRoot(TodoListPage, {});
        },
        error => {
          // console.log('Error:', error);
          if (error.status === 401 || error.status === 406) {
            // let message = JSON.parse(error._body).error;
            // this.notificationService.error(message);
            // console.log('Error:', message);
            this.user.pass = '';
          };
          loading.dismiss();
        }/*,
        () => {
          loading.dismiss();
        }*/);
    } catch (ex) {
      console.log('Exception:', ex);
      loading.dismiss();
      // TODO: mostrar erro? loggar exception
    }
  }
}
