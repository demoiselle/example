import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';
import { NotificationService } from '../../core/notification.service';

@Component({
  selector: 'app-amnesia',
  templateUrl: './amnesia.component.html'
})
export class AmnesiaComponent implements OnInit {
  user: any = {
    username: ''
  };

  constructor(protected authService: AuthService,
    protected router: Router,
    protected notificationService: NotificationService) { }

  ngOnInit() {
    console.log('[AmnesiaComponent] initialized.');
  }

  amnesia() {
    this.authService.amnesia(this.user)
      .subscribe(
      res => {
        this.notificationService.success('Enviamos um email para você!');
      },
      error => {
        if (error.status === 401 || error.status === 406) {
          let errors = error.error;
          for (const err of errors) {
            this.notificationService.error(err.error);
          }
          this.user.password = '';
        }
      });
  }
}
