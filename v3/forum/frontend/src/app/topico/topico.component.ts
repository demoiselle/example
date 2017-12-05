import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

import { NotificationService } from '../core/notification.service';
import { TopicoService } from './topico.service';
import { Topico } from './topico.model';

@Component({
  selector: 'app-topico',
  templateUrl: './topico.component.html',
  styleUrls: ['./topico.component.scss']

})
export class TopicoComponent implements OnInit {
  topico: Topico;
  topicos: Topico[];

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
      categoria: '',
      description: '',
  };
  public filterActive = true;
  public filters = {
      id: '',
      categoria: '',
      description: '',
  };

  public selecteds = [];

  constructor(private service: TopicoService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    console.debug('[TopicoComponent] created.');
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
          this.topicos = result.body;
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.topicos = [];
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
        console.debug('TopicoComponent.list() - completed.');
        this.isLoading = false;
      }
    );
  }

  delete(topico: Topico) {
    this.isLoading = true;
    this.service.delete(topico.id).subscribe(
      (result) => {
        this.topico = null;
        this.staticModalDelete.hide();
        this.notificationService.success("Topico removido com sucesso!");
        this.list();
      },
      (error) => {
        console.error(error);
        this.notificationService.error('Não foi possível remover!');
      },
      () => {
        console.debug('TopicoComponent.delete() - completed.');
        this.isLoading = false;
      }
    );
  }

  deleteSelecteds() {
    this.selecteds.map(selectedItem => {
      this.delete(selectedItem);
    });
  }

  toggleSelected(topico: Topico) {
    let index = this.selecteds.indexOf(topico);
    if(index === -1) {
      this.selecteds.push(topico);
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
