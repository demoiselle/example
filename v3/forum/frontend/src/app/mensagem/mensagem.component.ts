import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

import { NotificationService } from '../core/notification.service';
import { MensagemService } from './mensagem.service';
import { Mensagem } from './mensagem.model';

@Component({
  selector: 'app-mensagem',
  templateUrl: './mensagem.component.html',
  styleUrls: ['./mensagem.component.scss']

})
export class MensagemComponent implements OnInit {
  mensagem: Mensagem;
  mensagems: Mensagem[];

  @ViewChild('staticModalDelete') public staticModalDelete: ModalDirective;

  // states
  public isLoading = true;

  // Pagination
  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  // Filter
  public ascValue = '⇧';
  public descValue = '⇩';
  public formOrder = {
      id: '',
      usuario: '',
      topico: '',
      description: '',
      datahora: '',
  };
  public filterActive = true;
  public filters = {
      id: '',
      usuario: '',
      topico: '',
      description: '',
      datahora: '',
  };

  public selecteds = [];

  constructor(private service: MensagemService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    console.debug('[MensagemComponent] created.');
    this.isLoading = false;

    // populate table
    this.list();
  }

  list(field: string = null, desc: boolean = false) {
    this.isLoading = true;
    const filter = this.processFilter();
    this.service.findAll(this.currentPage, this.itemsPerPage, filter, field, desc).subscribe(
      (result) => {
        try {
          this.mensagems = result.body;
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.mensagems = [];
        }
        const contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        console.error(error);
        this.notificationService.error('Não foi possível carregar a lista de itens!');
      },
      () => {
        console.debug('MensagemComponent.list() - completed.');
        this.isLoading = false;
      }
    );
  }

  delete(mensagem: Mensagem) {
    this.isLoading = true;
    this.service.delete(mensagem.id).subscribe(
      (result) => {
        this.mensagem = null;
        this.staticModalDelete.hide();
        this.notificationService.success("Mensagem removido com sucesso!");
        this.list();
      },
      (error) => {
        console.error(error);
        this.notificationService.error('Não foi possível remover!');
      },
      () => {
        console.debug('MensagemComponent.delete() - completed.');
        this.isLoading = false;
      }
    );
  }

  deleteSelecteds() {
    this.selecteds.map(selectedItem => {
      this.delete(selectedItem);
    });
  }

  toggleSelected(mensagem: Mensagem) {
    let index = this.selecteds.indexOf(mensagem);
    if(index === -1) {
      this.selecteds.push(mensagem);
    } else {
      this.selecteds.splice(index, 1);
    }
  }

  showModalDelete() {
    if(this.selecteds.length > 0) {
      this.staticModalDelete.show();
    } else {
      console.warn('Nenhum item selecionado.');
    }
  }

  hideStaticModals() {
    this.staticModalDelete.hide();
  }

  // Pagination
  pageChanged(event: any) {
    this.currentPage = event.page;
    this.list();
  }

  onPageItemsChange(itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }

  // Filter
  processFilter() {
    let filter = '';
    for (let key in this.filters) {
      if (this.filters[key] !== '') {
        filter += `&${key}=${this.filters[key]}`;
      }
    }

    return filter;
  }

  orderBy(field) {
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
