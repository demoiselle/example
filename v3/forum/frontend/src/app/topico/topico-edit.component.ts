import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { TopicoService } from './topico.service';
import { Topico } from './topico.model';

@Component({
  selector: 'app-topico-edit',
  templateUrl: './topico-edit.component.html'
})
export class TopicoEditComponent implements OnInit {
  topico: Topico;
  
  private funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TopicoService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['topico']) {
      this.topico = this.route.snapshot.data['topico'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.topico = new Topico();
    }
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
  
  dalete(topico:Topico) {
    if (topico.id) {
      this.service.delete(topico).subscribe(
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
    this.router.navigate(['topico']);
  }

}