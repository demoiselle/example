import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { ModeratorService } from './moderator.service';
import { Moderator } from './moderator.model';

@Component({
  selector: 'my-moderator-edit',
  templateUrl: './moderator-edit.component.html'
})
export class ModeratorEditComponent implements OnInit {
  moderator: Moderator = new Moderator();
  id: number;
  moderatorLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: ModeratorService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.moderator.id = (<Moderator> params).id;
        this.moderator.description = (<Moderator> params).description;
      }
    });
  }

  loadModerator() {
    this.service.get(this.id)
      .subscribe(
      (Moderator: Moderator) => {
        this.moderator = Moderator;
        this.moderatorLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
  }

  save(moderator:Moderator) {
    if (!moderator.id) {
      delete moderator.id;
      this.service.create(moderator).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(moderator).subscribe(
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
    this.router.navigate(['moderator']);
  }

}
