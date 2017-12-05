import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

import { NotificationService } from '../core/notification.service';
import { CategoriaService } from './categoria.service';
import { Categoria } from './categoria.model';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.scss']

})
export class CategoriaComponent implements OnInit {
  categoria: Categoria;
  categorias: Categoria[];

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
      description: '',
  };
  public filterActive = true;
  public filters = {
      id: '',
      description: '',
  };

  public selecteds = [];

  constructor(private service: CategoriaService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    console.debug('[CategoriaComponent] created.');
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
          this.categorias = result.body;
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.categorias = [];
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
        console.debug('CategoriaComponent.list() - completed.');
        this.isLoading = false;
      }
    );
  }

  delete(categoria: Categoria) {
    this.isLoading = true;
    this.service.delete(categoria.id).subscribe(
      (result) => {
        this.categoria = null;
        this.staticModalDelete.hide();
        this.notificationService.success("Categoria removido com sucesso!");
        this.list();
      },
      (error) => {
        console.error(error);
        this.notificationService.error('Não foi possível remover!');
      },
      () => {
        console.debug('CategoriaComponent.delete() - completed.');
        this.isLoading = false;
      }
    );
  }

  deleteSelecteds() {
    this.selecteds.map(selectedItem => {
      this.delete(selectedItem);
    });
  }

  toggleSelected(categoria: Categoria) {
    let index = this.selecteds.indexOf(categoria);
    if(index === -1) {
      this.selecteds.push(categoria);
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
