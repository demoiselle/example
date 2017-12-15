import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../core/notification.service';
import { UserService } from './user.service';
import { User } from './user.model';
import { UtilService } from '../core/util.service';

const ACTIONS = {
  CRIAR: 'Criar',
  EDITAR: 'Editar'
};

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html'
})
export class UserEditComponent implements OnInit {
  user: User;
  perfilOptions;

  action = ACTIONS.CRIAR;
  isLoading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: UserService,
    private utilSerivce: UtilService,
    private notificationService: NotificationService
  ) { }

  ngOnInit() {
    if (this.route.snapshot.data['user']) {
      this.user = this.route.snapshot.data['user'];
      this.action = ACTIONS.EDITAR;
    } else {
      this.action = ACTIONS.CRIAR;
      this.user = new User();
    }

    this.populateCombo();
  }

  populateCombo() {
    const entitiesNames = [{
      entityName: 'perfil',
      endpoint: 'perfils'
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
      if (this.user && this.user[entityName]) {
          this.user[entityName] = data.find(i => i.id === this.user[entityName].id);
      }
  }

  startLoading() {
    this.isLoading = true;
  }

  endLoading() {
    this.isLoading = false;
  }

  save(user: User) {
    this.startLoading();
    if (!user.id) {
      delete user.id;
      this.service.create(user).subscribe(
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
      this.service.update(user).subscribe(
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
    if (this.user.id) {
      this.service.delete(this.user.id).subscribe(
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
    this.router.navigate(['user']);
  }

}
