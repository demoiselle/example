import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { GuestService } from './guest.service';
import { Guest } from './guest.model';

@Component({
  selector: 'my-guest',
  templateUrl: './guest.component.html',
  styleUrls: ['./guest.component.scss']

})
export class GuestComponent implements OnInit {
  guest: Guest;
  guests: Guest[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: GuestService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  showModalDetails(guest: Guest) {
    this.guest = guest;
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
          this.guests = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.guests = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.guests = error;
      }
    );
  }

  edit(guest:Guest) {
    this.service.update(guest).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(guest: Guest) {
    this.service.delete(guest).subscribe(
      (result) => {
        this.guest = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
