import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { CategoriaService } from './categoria.service';
import { Categoria } from './categoria.model';

@Component({
  selector: 'my-categoria-edit',
  templateUrl: './categoria-edit.component.html'
})
export class CategoriaEditComponent implements OnInit {
  categoria: Categoria = new Categoria();
  id: number;
  categoriaLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CategoriaService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.categoria.id = (<Categoria> params).id;
        this.categoria.description = (<Categoria> params).description;
      }
    });
  }

  loadCategoria() {
    this.service.get(this.id)
      .subscribe(
      (Categoria: Categoria) => {
        this.categoria = Categoria;
        this.categoriaLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
  }

  save(categoria:Categoria) {
    if (!categoria.id) {
      delete categoria.id;
      this.service.create(categoria).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(categoria).subscribe(
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
    this.router.navigate(['categoria']);
  }

}
