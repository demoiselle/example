import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { CategoriaService } from './categoria.service';
import { Categoria } from './categoria.model';

@Component({
  selector: 'my-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.scss']

})
export class CategoriaComponent implements OnInit {
  categoria: Categoria;
  categorias: Categoria[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: CategoriaService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  showModalDetails(categoria: Categoria) {
    this.categoria = categoria;
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

  edit(categoria:Categoria) {
    this.service.update(categoria).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(categoria: Categoria) {
    this.service.delete(categoria).subscribe(
      (result) => {
        this.categoria = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
