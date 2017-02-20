import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { MensagemService } from './mensagem.service';
import { Mensagem } from './mensagem.model';

@Component({
  selector: 'my-mensagem',
  templateUrl: './mensagem.component.html',
  styleUrls: ['./mensagem.component.scss']

})
export class MensagemComponent implements OnInit {
  mensagem: Mensagem;
  mensagems: Mensagem[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: MensagemService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  showModalDetails(mensagem: Mensagem) {
    this.mensagem = mensagem;
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
          this.mensagems = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.mensagems = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.mensagems = error;
      }
    );
  }

  edit(mensagem:Mensagem) {
    this.service.update(mensagem).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(mensagem: Mensagem) {
    this.service.delete(mensagem).subscribe(
      (result) => {
        this.mensagem = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
