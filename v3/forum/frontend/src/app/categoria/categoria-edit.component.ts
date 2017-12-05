import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../core/notification.service';
import { CategoriaService } from './categoria.service';
import { Categoria } from './categoria.model';

const ACTIONS = {
  CRIAR: 'Criar',
  EDITAR: 'Editar'
};

@Component({
  selector: 'app-categoria-edit',
  templateUrl: './categoria-edit.component.html'
})
export class CategoriaEditComponent implements OnInit {
  categoria: Categoria;

  action = ACTIONS.CRIAR;
  isLoading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CategoriaService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['categoria']) {
      this.categoria = this.route.snapshot.data['categoria'];
      this.action = ACTIONS.EDITAR;
    } else {
      this.action = ACTIONS.CRIAR;
      this.categoria = new Categoria();
    }
  }

  startLoading() {
    this.isLoading = true;
  }

  endLoading() {
    this.isLoading = false;
  }

  save(categoria:Categoria) {
    this.startLoading();
    if (!categoria.id) {
      delete categoria.id;
      this.service.create(categoria).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        },
        () => {
          this.endLoading();
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
        },
        () => {
          this.endLoading();
        }
      );
    }
  }

  remove() {
    this.startLoading();
    if (this.categoria.id) {
      this.service.delete(this.categoria.id).subscribe(
        (result) => {
          this.notificationService.success('Item removido com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível deletar o Item!');
        },
        () => {
          this.endLoading();
        }
      );
    } else {
      console.warn('Item sem ID!?');
      this.endLoading();
    }
  }

  goBack() {
    this.router.navigate(['categoria']);
  }

}
