import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { TesteService } from './teste.service';
import { Teste } from './teste.model';

@Component({
  selector: 'app-teste-edit',
  templateUrl: './teste-edit.component.html'
})
export class TesteEditComponent implements OnInit {
  teste: Teste;
  
  funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TesteService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['teste']) {
      this.teste = this.route.snapshot.data['teste'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.teste = new Teste();
    }
  }


  save(teste:Teste) {
    if (!teste.id) {
      delete teste.id;
      this.service.create(teste).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(teste).subscribe(
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
  
  dalete(teste:Teste) {
    if (teste.id) {
      this.service.delete(teste).subscribe(
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
    this.router.navigate(['teste']);
  }

}