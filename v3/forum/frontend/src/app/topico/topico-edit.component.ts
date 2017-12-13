import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../core/notification.service';
import { TopicoService } from './topico.service';
import { Topico } from './topico.model';
import { UtilService } from '../core/util.service';

const ACTIONS = {
  CRIAR: 'Criar',
  EDITAR: 'Editar'
};

@Component({
  selector: 'app-topico-edit',
  templateUrl: './topico-edit.component.html'
})
export class TopicoEditComponent implements OnInit {
  topico: Topico;
  categoriaOptions;

  action = ACTIONS.CRIAR;
  isLoading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TopicoService,
    private utilSerivce: UtilService,
    private notificationService: NotificationService
  ) { }

  ngOnInit() {
    if (this.route.snapshot.data['topico']) {
      this.topico = this.route.snapshot.data['topico'];
      this.action = ACTIONS.EDITAR;
    } else {
      this.action = ACTIONS.CRIAR;
      this.topico = new Topico();
    }

    this.populateCombo();
  }

  populateCombo() {
    const entitiesNames = [{
      entityName: 'categoria',
      endpoint: 'categorias'
    }];
    const entitiesEndpoint = entitiesNames.map(e => e.endpoint);

    this.utilSerivce.loadAllEntityListAsPromise(entitiesEndpoint).then(results => {
        entitiesNames.forEach((val, index, arr) => {
            this[val.entityName + 'Options'] = results[index];
            this.updateCombo(val.entityName, results[index]);
        });
    });
  }

  updateCombo(entityName, data) {
      if (this.topico && this.topico[entityName]) {
          this.topico[entityName] = data.find(i => i.id === this.topico[entityName].id);
      }
  }

  startLoading() {
    this.isLoading = true;
  }

  endLoading() {
    this.isLoading = false;
  }

  save(topico: Topico) {
    this.startLoading();
    if (!topico.id) {
      delete topico.id;
      this.service.create(topico).subscribe(
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
      this.service.update(topico).subscribe(
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
    if (this.topico.id) {
      this.service.delete(this.topico.id).subscribe(
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
    this.router.navigate(['topico']);
  }

}
