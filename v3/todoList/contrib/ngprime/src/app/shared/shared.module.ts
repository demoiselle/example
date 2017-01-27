import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SecurityModule } from '@demoiselle/security';

import { NotificationService } from './notification.service';
import { LoginService } from '../login/shared/login.service';
import { UserDropdownComponent } from './top-nav/user-dropdown.component';
import { SidebarMenuComponent } from './sidebar-menu/sidebar-menu.component';
import { TopNavComponent } from './top-nav/top-nav.component';

import {MenubarModule,MenuItem} from 'primeng/primeng';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,

    MenubarModule
  ],
  declarations: [SidebarMenuComponent, TopNavComponent, UserDropdownComponent],
  providers: [
    NotificationService,
    LoginService
  ],
  exports: [
    CommonModule,
    FormsModule,
    RouterModule,
    SecurityModule,

    SidebarMenuComponent,
    TopNavComponent,
    UserDropdownComponent
  ]
})
export class SharedModule { }
