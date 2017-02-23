import { Component, OnInit, OnDestroy, Output } from '@angular/core';
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

  @Output()
  public selectedRole;
  public roles = [
    {value: 'loading...', description: 'loading...'}
  ];

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: UserService,
    private loginService: LoginService,
    private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.loadPerfil();
    this.routeSubscribe = this.route.params.subscribe(params => {
      if (params['id']) {
        this.id = +params['id']; // (+) converts string 'id' to a number
        this.loadUsuario();
      } else {
        this.user = new User();
        this.userLoaded = true;
      }
    });
    this.selectedRole = this.roles[0];
  }

  ngOnDestroy() {
    this.routeSubscribe.unsubscribe();
  }

    loadPerfil() {
    this.service.getPerfil().subscribe(
      (result) => {
        this.roles = [];
        Object.keys(result).forEach(element => {
          console.log(element);
          this.roles.push({value: element, description: result[element]});
        });
        console.log(this.roles);
        if (this.roles.length > 0) {
          this.selectedRole = this.roles[0];
        }
      },
      (error) => {
        console.log('error');
        console.log(error);
        this.notificationService.error('Não foi possível carregar a lista de perfis!');
      }
    );
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
    this.user.perfil = this.selectedRole.value;
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

  changeRole(event) {
    this.user.perfil = event.target.value;
    this.roles.forEach(element => {
      if (element.description == this.user.perfil) {
        this.selectedRole = element;
      }
    });
  }
}
