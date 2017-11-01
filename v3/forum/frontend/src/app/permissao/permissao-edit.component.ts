import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { PermissaoService } from './permissao.service';
import { Permissao } from './permissao.model';

@Component({
  selector: 'app-permissao-edit',
  templateUrl: './permissao-edit.component.html'
})
export class PermissaoEditComponent implements OnInit {
  permissao: Permissao;
  
  funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: PermissaoService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['permissao']) {
      this.permissao = this.route.snapshot.data['permissao'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.permissao = new Permissao();
    }
  }


  save(permissao:Permissao) {
    if (!permissao.id) {
      delete permissao.id;
      this.service.create(permissao).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(permissao).subscribe(
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
  
  dalete(permissao:Permissao) {
    if (permissao.id) {
      this.service.delete(permissao).subscribe(
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
    this.router.navigate(['permissao']);
  }

}