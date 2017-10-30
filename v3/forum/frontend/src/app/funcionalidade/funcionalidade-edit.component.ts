import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { FuncionalidadeService } from './funcionalidade.service';
import { Funcionalidade } from './funcionalidade.model';

@Component({
  selector: 'app-funcionalidade-edit',
  templateUrl: './funcionalidade-edit.component.html'
})
export class FuncionalidadeEditComponent implements OnInit {
  funcionalidade: Funcionalidade;
  
  funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: FuncionalidadeService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['funcionalidade']) {
      this.funcionalidade = this.route.snapshot.data['funcionalidade'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.funcionalidade = new Funcionalidade();
    }
  }


  save(funcionalidade:Funcionalidade) {
    if (!funcionalidade.id) {
      delete funcionalidade.id;
      this.service.create(funcionalidade).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(funcionalidade).subscribe(
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
  
  dalete(funcionalidade:Funcionalidade) {
    if (funcionalidade.id) {
      this.service.delete(funcionalidade).subscribe(
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
    this.router.navigate(['funcionalidade']);
  }

}