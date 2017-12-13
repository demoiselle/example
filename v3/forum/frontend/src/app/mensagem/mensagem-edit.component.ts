import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../core/notification.service';
import { MensagemService } from './mensagem.service';
import { Mensagem } from './mensagem.model';
import { UtilService } from '../core/util.service';

const ACTIONS = {
  CRIAR: 'Criar',
  EDITAR: 'Editar'
};

@Component({
  selector: 'app-mensagem-edit',
  templateUrl: './mensagem-edit.component.html'
})
export class MensagemEditComponent implements OnInit {
  mensagem: Mensagem;
  usuarioOptions;
  topicoOptions;
  datahoraOptions;

  action = ACTIONS.CRIAR;
  isLoading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: MensagemService,
    private utilSerivce: UtilService,
    private notificationService: NotificationService
  ) { }

  ngOnInit() {
    if (this.route.snapshot.data['mensagem']) {
      this.mensagem = this.route.snapshot.data['mensagem'];
      this.action = ACTIONS.EDITAR;
    } else {
      this.action = ACTIONS.CRIAR;
      this.mensagem = new Mensagem();
    }

    this.populateCombo();
  }

  populateCombo() {
    const entitiesNames = [{
      entityName: 'usuario',
      endpoint: 'users'
    }, {
      entityName: 'topico',
      endpoint: 'topicos'
    }, {
      entityName: 'datahora',
      endpoint: 'dates'
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
      if (this.mensagem && this.mensagem[entityName]) {
          this.mensagem[entityName] = data.find(i => i.id === this.mensagem[entityName].id);
      }
  }

  startLoading() {
    this.isLoading = true;
  }

  endLoading() {
    this.isLoading = false;
  }

  save(mensagem: Mensagem) {
    this.startLoading();
    if (!mensagem.id) {
      delete mensagem.id;
      this.service.create(mensagem).subscribe(
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
      this.service.update(mensagem).subscribe(
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
    if (this.mensagem.id) {
      this.service.delete(this.mensagem.id).subscribe(
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
    this.router.navigate(['mensagem']);
  }

}
