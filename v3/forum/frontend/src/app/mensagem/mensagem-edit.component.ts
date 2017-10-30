import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { MensagemService } from './mensagem.service';
import { Mensagem } from './mensagem.model';

@Component({
  selector: 'app-mensagem-edit',
  templateUrl: './mensagem-edit.component.html'
})
export class MensagemEditComponent implements OnInit {
  mensagem: Mensagem;
  
  funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: MensagemService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['mensagem']) {
      this.mensagem = this.route.snapshot.data['mensagem'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.mensagem = new Mensagem();
    }
  }


  save(mensagem:Mensagem) {
    if (!mensagem.id) {
      delete mensagem.id;
      this.service.create(mensagem).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(mensagem).subscribe(
        (result) => {
          this.notificationService.success('Item alterado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível alterar!');
        }
      );
    }
  }
  
  dalete(mensagem:Mensagem) {
    if (mensagem.id) {
      this.service.delete(mensagem).subscribe(
        (result) => {
          this.notificationService.success('Item removido com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível deletar o Item!');
        }
      );

    }
  }

  goBack() {
    this.router.navigate(['mensagem']);
  }

}