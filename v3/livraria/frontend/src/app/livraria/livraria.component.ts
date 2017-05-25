import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { LivrariaService } from './livraria.service';
import { Livraria } from './livraria.model';

@Component({
  selector: 'my-livraria',
  templateUrl: './livraria.component.html',
  styleUrls: ['./livraria.component.scss']

})
export class LivrariaComponent implements OnInit {
  livraria: Livraria;
  livrarias: Livraria[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: LivrariaService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  showModalDetails(livraria: Livraria) {
    this.livraria = livraria;
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
          this.livrarias = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.livrarias = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.livrarias = error;
      }
    );
  }

  edit(livraria:Livraria) {
    this.service.update(livraria).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(livraria: Livraria) {
    this.service.delete(livraria).subscribe(
      (result) => {
        this.livraria = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
