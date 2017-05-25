import { Component, OnInit, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { UserService } from './user.service';
import { User } from './user.model';

@Component({
  selector: 'my-user-edit',
  templateUrl: './user-edit.component.html'
})
export class UserEditComponent implements OnInit {
  user: User;
  confirmPass: string;
  params: any;
  userLoaded: boolean = false;

  @Output()
  public selectedRole;
  public roles = [
    {value: 'GERENTE', description: 'Gerente'},
    {value: 'USUARIO', description: 'Usuário'},
    {value: 'ADMINISTRADOR', description: 'Administrador'}
  ];

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: UserService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  {
    this.user = new User();
    this.user.id = null;
    this.user.firstName = 'Admin';
    this.user.perfil = 'Administrador';
    this.user.email = 'admin@demoiselle.org';
    this.user.pass = '12345678';
    this.confirmPass = '12345678';
    this.selectedRole = this.roles[0];
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.user.id = (<User> params).id;
        this.user.firstName = (<User> params).firstName;
        this.user.email = (<User> params).email;
        this.user.pass = (<User> params).pass;
        this.user.perfil = (<User> params).perfil;
        this.loadPerfil(this.user.perfil);
        this.confirmPass = this.user.pass;
      } else {
        this.loadPerfil(null);
      }
    });
  }

  loadPerfil(perfil) {
    this.service.getPerfil().subscribe(
      (result) => {
        this.roles = [];
        Object.keys(result).forEach(element => {
          this.roles.push({value: element, description: result[element]});
        });
        if (this.roles.length > 0) {
          if (perfil) {
            this.updateSelectedRole(this.user.perfil);
          } else {
            this.selectedRole = this.roles[0];
          }
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de perfis!');
      }
    );
  }

  save(user:User) {
    user.perfil = this.selectedRole.value;
    if (!user.id) {
      delete user.id;
      this.service.create(user).subscribe(
        (result) => {
          this.notificationService.success('Usuário criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar o usuário!');
        }
      );
    }
    else {
      this.service.update(user).subscribe(
        (result) => {
          this.notificationService.success('Usuário alterado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível alterar o usuário!');
        }
      );
    }
  }
  
  goBack() {
    this.router.navigate(['user']);
  }

  changeRole(event) {
    this.user.perfil = event.target.value;
    this.updateSelectedRole(this.user.perfil);
  }

  updateSelectedRole(perfil) {
    this.roles.forEach(element => {
      if (element.value == perfil || element.description == perfil) {
        this.selectedRole = element;
      }
    });
  }
}
