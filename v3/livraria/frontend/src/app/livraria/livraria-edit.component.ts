import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { LivrariaService } from './livraria.service';
import { Livraria } from './livraria.model';

@Component({
  selector: 'my-livraria-edit',
  templateUrl: './livraria-edit.component.html'
})
export class LivrariaEditComponent implements OnInit {
  livraria: Livraria = new Livraria();
  id: number;
  livrariaLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: LivrariaService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.livraria.id = (<Livraria> params).id;
        this.livraria.description = (<Livraria> params).description;
      }
    });
  }

  loadLivraria() {
    this.service.get(this.id)
      .subscribe(
      (Livraria: Livraria) => {
        this.livraria = Livraria;
        this.livrariaLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
  }

  save(livraria:Livraria) {
    if (!livraria.id) {
      delete livraria.id;
      this.service.create(livraria).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(livraria).subscribe(
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
    this.router.navigate(['livraria']);
  }

}
