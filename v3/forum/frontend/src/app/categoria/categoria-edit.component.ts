import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { CategoriaService } from './categoria.service';
import { Categoria } from './categoria.model';

@Component({
  selector: 'app-categoria-edit',
  templateUrl: './categoria-edit.component.html'
})
export class CategoriaEditComponent implements OnInit {
  categoria: Categoria;
  
  private funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CategoriaService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['categoria']) {
      this.categoria = this.route.snapshot.data['categoria'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.categoria = new Categoria();
    }
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
  
  dalete(categoria:Categoria) {
    if (categoria.id) {
      this.service.delete(categoria).subscribe(
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
    this.router.navigate(['categoria']);
  }

}