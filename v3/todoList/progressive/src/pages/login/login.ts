import { Component } from '@angular/core';
import { NavController, NavParams, LoadingController } from 'ionic-angular';
import { ToastController } from 'ionic-angular';
// import { AlertController } from 'ionic-angular';

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
    public loadingCtrl: LoadingController,
    public toastCtrl: ToastController) {
  }

  ionViewDidLoad() {
    // console.log('ionViewDidLoad LoginPage');
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
          this.toastCtrl.create({
            message: 'Usuário logado com sucesso!',
            duration: 3000,
            showCloseButton: true
          }).present();
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
          // this.toastCtrl.create({
          //   message: 'Incorrect user or password',
          //   duration: 3000,
          //   showCloseButton: true
          // }).present();
        },
        () => {
          // console.log('Complete.');
          loading.dismiss();
        });
    } catch (ex) {
      // console.log('Exception:', ex);
      this.toastCtrl.create({
        message: 'Error with authentication',
        duration: 3000,
        showCloseButton: true
      }).present();
      loading.dismiss();
      // TODO: mostrar erro? loggar exception
    }
  }
}
