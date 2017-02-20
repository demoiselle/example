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
  id: number;
  params: any;
  userLoaded: boolean = false;

  @Output()
  public selectedRole;
  public roles = [
    {value: 'USUARIO', description: 'Usuário'},
    {value: 'GERENTE', description: 'Gerente'},
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
    this.selectedRole = this.roles[0];
  }

  ngOnInit() {
    this.loadPerfil();
    this.route.params.subscribe(params => {
      console.log(params);
      if (Object.keys(params).length > 0) {
        this.user.id = (<User> params).id;
        this.user.firstName = (<User> params).firstName;
        this.user.email = (<User> params).email;
        this.user.pass = (<User> params).pass;
        this.user.perfil = (<User> params).perfil;
        this.roles.forEach(element => {
        console.log(element.value + ' ' + this.user.perfil);
          if (element.value == this.user.perfil) {
            this.selectedRole = element;
            this.user.perfil = element.description;
          }
        });
        console.log('this.selectedRole');
        console.log(this.selectedRole);
      }
    });
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
      (user: User) => {
        this.user = user;
        this.userLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar usuário');
      }
      );
  }

  save(user:User) {
    this.roles.forEach(element => {
      if (element.description == this.user.perfil) {
        this.user.perfil = element.value;
      }
    });
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
    this.roles.forEach(element => {
      if (element.description == this.user.perfil) {
        this.selectedRole = element;
      }
    });
  }
}
