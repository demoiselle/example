import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { PerfilService } from './perfil.service';
import { Perfil } from './perfil.model';

@Component({
  selector: 'app-perfil-edit',
  templateUrl: './perfil-edit.component.html'
})
export class PerfilEditComponent implements OnInit {
  perfil: Perfil;
  
   funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: PerfilService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['perfil']) {
      this.perfil = this.route.snapshot.data['perfil'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.perfil = new Perfil();
    }
  }


  save(perfil:Perfil) {
    if (!perfil.id) {
      delete perfil.id;
      this.service.create(perfil).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(perfil).subscribe(
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
  
  dalete(perfil:Perfil) {
    if (perfil.id) {
      this.service.delete(perfil).subscribe(
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
    this.router.navigate(['perfil']);
  }

}