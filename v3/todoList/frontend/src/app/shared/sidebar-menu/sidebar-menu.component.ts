import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from '@demoiselle/security';
import { LoginService } from '../../login/shared/login.service';

// webpack html imports
let template = require('./sidebar-menu.template.html');

@Component({
  selector: 'todo-sidebar-menu',
  styleUrls: ['./sidebar-menu.component.scss'],
  template
})
export class SidebarMenuComponent {
  public search: any = {};
  public hash = '';
  public routes: any[] = [];

  public constructor(private router: Router, private authService: AuthService, private loginService: LoginService) {
    this.router.events.subscribe((event: any) => {
      if (event instanceof NavigationEnd) {
        this.hash = event.url;
      }
    });

    for (let i = 0; i < this.router.config.length; i++) {
      if (this.router.config[i] && this.router.config[i].data) {
        this.routes.push(this.router.config[i]);
      }
    }
  }

}
