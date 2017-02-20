import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { MensagemService } from './mensagem.service';
import { Mensagem } from './mensagem.model';

@Component({
  selector: 'my-mensagem-edit',
  templateUrl: './mensagem-edit.component.html'
})
export class MensagemEditComponent implements OnInit {
  mensagem: Mensagem = new Mensagem();
  id: number;
  mensagemLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: MensagemService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.mensagem.id = (<Mensagem> params).id;
        this.mensagem.description = (<Mensagem> params).description;
      }
    });
  }

  loadMensagem() {
    this.service.get(this.id)
      .subscribe(
      (Mensagem: Mensagem) => {
        this.mensagem = Mensagem;
        this.mensagemLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
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
  
  goBack() {
    this.router.navigate(['mensagem']);
  }

}
