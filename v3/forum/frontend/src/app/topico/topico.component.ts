import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { TopicoService } from './topico.service';
import { Topico } from './topico.model';

@Component({
  selector: 'my-topico',
  templateUrl: './topico.component.html',
  styleUrls: ['./topico.component.scss']

})
export class TopicoComponent implements OnInit {
  topico: Topico;
  topicos: Topico[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: TopicoService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  showModalDetails(topico: Topico) {
    this.topico = topico;
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
          this.topicos = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.topicos = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.topicos = error;
      }
    );
  }

  edit(topico:Topico) {
    this.service.update(topico).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(topico: Topico) {
    this.service.delete(topico).subscribe(
      (result) => {
        this.topico = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
