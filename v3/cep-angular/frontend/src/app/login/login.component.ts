import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';
import { NotificationService } from '../shared';
import { LoginService } from './login.service';

@Component({
  selector: 'my-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  user: any = {
    username: 'admin@demoiselle.org',
    password: '123456'
  };

  constructor(protected authService: AuthService,
    protected router: Router,
    protected notificationService: NotificationService,
    protected loginService: LoginService) { }

  onSubmit($event, form){
    this.login();
  }

  login() {
    this.loginService.login(this.user)
      .subscribe(
      (result) => {
        this.router.navigate(['']);
      },
      (error) => {
        if (error.status === 401 || error.status === 406) {
          let errors = JSON.parse(error._body);
          for (let err of errors) {
            this.notificationService.error(err.error);
          }
          this.user.password = '';
        };
      });
  }

}
