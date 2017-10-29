import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

import { NotificationService } from '../shared';
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

  constructor(private service: CategoriaService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  

  list(field: string = null, desc: boolean = false) {
    let filter = this.processFilter();
    this.service.list(/*this.pagination.currentPage*/ 1, this.itemsPerPage, filter, field, desc).subscribe(
      (result) => {
        try {
          this.categorias = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.categorias = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.categorias = error;
      }
    );
  }

  delete(categoria: Categoria) {
    this.service.delete(categoria).subscribe(
      (result) => {
        this.categoria = null;
        this.staticModalDelete.hide();
        this.notificationService.success("Categoria removido com sucesso!");
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }

  showModalDelete(categoria: Categoria) {
    this.categoria = categoria;
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
