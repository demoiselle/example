import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../../shared';
import { LoginService } from '../../login/shared/login.service';
import { UserService } from '../shared/user.service';
import { User } from '../shared/user.model';

@Component({
  selector: 'todo-user-edit',
  templateUrl: './user-edit.component.html'
})
export class UserEditComponent implements OnInit, OnDestroy {
  user: User;
  id: number;
  userLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: UserService,
    private loginService: LoginService,
    private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.routeSubscribe = this.route.params.subscribe(params => {
      if (params['id']) {
        this.id = +params['id']; // (+) converts string 'id' to a number
        this.loadUsuario();
      } else {
        this.user = new User();
        this.userLoaded = true;
      }
    });
  }

  ngOnDestroy() {
    this.routeSubscribe.unsubscribe();
  }

  loadUsuario() {
    this.service.get(this.id)
      .subscribe(
      (usuario: User) => {
        this.user = usuario;
        this.userLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar usuário');
      }
      );
  }

  save() {
    if (!this.id) {
      this.service.create(this.user).subscribe(
        () => {
          this.notificationService.success('Usuário criado com sucesso!');
          this.back();
        },
        error => {
          this.notificationService.error('Não foi possível salvar o usuário!');
        }
      );
    } else {
      this.service.update(this.user).subscribe(
        usuario => {
          this.notificationService.success('Usuário atualizado com sucesso!');
          this.back();
        },
        error => {
          this.notificationService.error('Não foi possível salvar o usuário!');
        }
      );
    }
  }

  back() {
    this.router.navigate(['']);
  }
}
