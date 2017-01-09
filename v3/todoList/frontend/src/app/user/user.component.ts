import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { UserService } from './shared/user.service';
import { User } from './shared/user.model';

@Component({
  selector: 'todo-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']

})
export class UserComponent implements OnInit {
  user: User;
  users: User[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: UserService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
    console.log('[UserComponent] initialized.');
  }

  showModalDetails(user: User) {
    this.user = user;
    this.staticModal.show();
  }

  pageChanged(event: any): void {
    this.currentPage = event.page;
    this.list();
  }

  list() {
    this.service.list(this.currentPage, this.itemsPerPage).subscribe(
      result => {
        this.totalItems = 20; // backend must send  the total items for proper pagination config
        this.users = result;
      },
      error => {
        this.notificationService.error('Não foi possível carregar a lista de usuarios!');
        this.totalItems = 20;
        this.users = error;
      }
    );
  }

  delete(usuario: User) {
    this.service.delete(usuario).subscribe(
      () => {
        this.user = null;
        this.staticModal.hide();
        this.list();
      },
      error => {
        this.notificationService.error('Não foi possível remover o usuário!');
      }
    );
  }
}
