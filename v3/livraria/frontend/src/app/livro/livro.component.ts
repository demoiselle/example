import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { LivroService } from './livro.service';
import { Livro } from './livro.model';

@Component({
  selector: 'my-livro',
  templateUrl: './livro.component.html',
  styleUrls: ['./livro.component.scss']

})
export class LivroComponent implements OnInit {
  livro: Livro;
  livros: Livro[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: LivroService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  showModalDetails(livro: Livro) {
    this.livro = livro;
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
          this.livros = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.livros = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.livros = error;
      }
    );
  }

  edit(livro:Livro) {
    this.service.update(livro).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(livro: Livro) {
    this.service.delete(livro).subscribe(
      (result) => {
        this.livro = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
