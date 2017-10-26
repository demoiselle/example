import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

import { NotificationService } from '../shared';
import { GuestService } from './guest.service';
import { Guest } from './guest.model';

@Component({
  selector: 'app-guest',
  templateUrl: './guest.component.html',
  styleUrls: ['./guest.component.scss']

})
export class GuestComponent implements OnInit {
  guest: Guest;
  guests: Guest[];

  @ViewChild('staticModalDelete') public staticModalDelete: ModalDirective;

  // Pagination
  public itemsPerPage: number = 10;
  public totalItems: number = 0;

  // Filter
  public ascValue = '⇧';
  public descValue = '⇩';
  public formOrder = {
    id: '',
    description: ''
  };

  public filterActive = true;
  public filters = {
    id: '',
    description: ''
  };

  constructor(private service: GuestService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  

  list(field: string = null, desc: boolean = false) {
    let filter = this.processFilter();
    this.service.list(/*this.pagination.currentPage*/ 1, this.itemsPerPage, filter, field, desc).subscribe(
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

  delete(guest: Guest) {
    this.service.delete(guest).subscribe(
      (result) => {
        this.guest = null;
        this.staticModalDelete.hide();
        this.notificationService.success("Guest removido com sucesso!");
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }

  showModalDelete(guest: Guest) {
    this.guest = guest;
    this.staticModalDelete.show();
  }

  hideStaticModals() {
    this.staticModalDelete.hide();
  }

  // Pagination
  onPageChange(currentPage) {
    this.list();
  }

  onPageItemsChange(itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }

   // Filter
  processFilter() {
    let filter = '';
    if (this.filters.id !== '') {
      filter += '&id=' + this.filters.id;
    }
    if (this.filters.description !== '') {
      filter += '&description=' + this.filters.description;
    }
    return filter;
  }

  
  orderBy(field, order) {
    this.toggleFormOrder(field);
    this.list(field, this.formOrder[field] === this.descValue ? true : false);
  }

  toggleFormOrder(field) {
    if (this.formOrder[field] === '') {
      this.formOrder[field] = this.ascValue;
    } else if (this.formOrder[field] === this.ascValue) {
      this.formOrder[field] = this.descValue;
    } else if (this.formOrder[field] === this.descValue) {
      this.formOrder[field] = this.ascValue;
    }
    for (let key in this.formOrder) {
      if (key !== field) {
        this.formOrder[key] = '';
      }
    }
  }

  clearFormOrder() {
    for (let key in this.formOrder) {
      this.formOrder[key] = '';
    }
  }
}
