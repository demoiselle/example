import { Component, OnInit } from '@angular/core';
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
  user: User = new User();
  id: number;
  userLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: UserService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.user.id = (<User> params).id;
        this.user.description = (<User> params).description;
      }
    });
  }

  loadUser() {
    this.service.get(this.id)
      .subscribe(
      (User: User) => {
        this.user = User;
        this.userLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
  }

  save(user:User) {
    if (!user.id) {
      delete user.id;
      this.service.create(user).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
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
        }
      );
    }
  }
  
  goBack() {
    this.router.navigate(['user']);
  }

}
