import { Component, ViewChild } from '@angular/core';
import { StatusBar, Splashscreen } from 'ionic-native';
import { Nav, Platform, Events } from 'ionic-angular';
import { ToastController } from 'ionic-angular';
// import { AlertController } from 'ionic-angular';

import { AuthService } from '@demoiselle/security';

import { TodoListPage } from '../pages/todo-list/todo-list';
import { LoginPage } from '../pages/login/login';

export const AppEvents = new Events();

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = TodoListPage;

  pages: Array<{ title: string, component: any }>;
  loginPage = { title: 'Login', component: LoginPage };
  // logoutPage = { title: 'Logout', component: LogoutPage };

  constructor(
    public platform: Platform,
    public authService: AuthService,
    public toastCtrl: ToastController
  ) {
    this.initializeApp();

    // used for an example of ngFor and navigation
    this.pages = [
      { title: 'To-Do\'s', component: TodoListPage }
    ];
  }

  initializeApp() {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      StatusBar.styleDefault();
      Splashscreen.hide();
    });

    AppEvents.subscribe('auth:unauthorizred', () => {
      this.toastCtrl.create({
        message: 'Acesso não autorizado.',
        duration: 3000,
        showCloseButton: true
      }).present();
      if (this.nav.getActive().component !== LoginPage) {
        this.openPage(this.rootPage);
      }
    });

    AppEvents.subscribe('auth:login-success', () => {
      this.toastCtrl.create({
        message: 'Usuário logado com sucesso!',
        duration: 3000,
        showCloseButton: true
      }).present();
      this.openPage(this.rootPage);
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }

  logout() {
    this.authService.logout();
    this.nav.setRoot(LoginPage);
  }
}
