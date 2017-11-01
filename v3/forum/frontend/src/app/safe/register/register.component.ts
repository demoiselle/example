import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';
import { NotificationService } from '../../shared';
import { RegisterService } from './register.service';

@Component({
  selector: 'todo-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
  user: any = {
    username: 'admin@demoiselle.org',
    password: '123456'
  };

  constructor(protected authService: AuthService,
    protected router: Router,
    protected notificationService: NotificationService,
    protected registerService: RegisterService) { }

  ngOnInit() {
    console.log('[RegisterComponent] initialized.');
  }

  register() {
    this.authService.register(this.user)
      .subscribe(
      res => {
        this.notificationService.success('Register realizado com sucesso!');
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
