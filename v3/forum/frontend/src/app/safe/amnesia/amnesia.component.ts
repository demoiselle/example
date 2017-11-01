import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';
import { NotificationService } from '../../shared';
import { AmnesiaService } from './amnesia.service';

@Component({
  selector: 'todo-amnesia',
  templateUrl: './amnesia.component.html'
})
export class AmnesiaComponent implements OnInit {
  user: any = {
    username: 'admin@demoiselle.org',
    password: '123456'
  };

  constructor(protected authService: AuthService,
    protected router: Router,
    protected notificationService: NotificationService,
    protected amnesiaService: AmnesiaService) { }

  ngOnInit() {
    console.log('[AmnesiaComponent] initialized.');
  }

  amnesia() {
    this.authService.amnesia(this.user)
      .subscribe(
      res => {
        this.notificationService.success('Amnesia realizado com sucesso!');
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
