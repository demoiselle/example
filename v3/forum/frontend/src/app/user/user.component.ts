import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { UserService } from './user.service';
import { User } from './user.model';

@Component({
  selector: 'my-user',
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
      (result) => {
        try {
          this.users = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.users = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.users = error;
      }
    );
  }

  edit(user:User) {
    this.service.update(user).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(user: User) {
    this.service.delete(user).subscribe(
      (result) => {
        this.user = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
