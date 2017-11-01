import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';
import { NotificationService } from '../../shared';
import { LoginService } from './login.service';

@Component({
  selector: 'todo-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  user: any = {
    username: 'admin@demoiselle.org',
    password: '123456'
  };

  constructor(protected authService: AuthService,
    protected router: Router,
    protected notificationService: NotificationService,
    protected loginService: LoginService) { }

  ngOnInit() {
    console.log('[LoginComponent] initialized.');
  }

  login() {
    this.authService.login(this.user)
      .subscribe(
      res => {
        this.notificationService.success('Login realizado com sucesso!');
      },
      error => {
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
