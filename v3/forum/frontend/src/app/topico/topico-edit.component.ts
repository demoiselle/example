import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { TopicoService } from './topico.service';
import { Topico } from './topico.model';

@Component({
  selector: 'my-topico-edit',
  templateUrl: './topico-edit.component.html'
})
export class TopicoEditComponent implements OnInit {
  topico: Topico = new Topico();
  id: number;
  topicoLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TopicoService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.topico.id = (<Topico> params).id;
        this.topico.description = (<Topico> params).description;
      }
    });
  }

  loadTopico() {
    this.service.get(this.id)
      .subscribe(
      (Topico: Topico) => {
        this.topico = Topico;
        this.topicoLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
  }

  save(topico:Topico) {
    if (!topico.id) {
      delete topico.id;
      this.service.create(topico).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(topico).subscribe(
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
    this.router.navigate(['topico']);
  }

}
