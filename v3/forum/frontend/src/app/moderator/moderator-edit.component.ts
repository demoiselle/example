import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { ModeratorService } from './moderator.service';
import { Moderator } from './moderator.model';

@Component({
  selector: 'app-moderator-edit',
  templateUrl: './moderator-edit.component.html'
})
export class ModeratorEditComponent implements OnInit {
  moderator: Moderator;
  
  private funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: ModeratorService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['moderator']) {
      this.moderator = this.route.snapshot.data['moderator'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.moderator = new Moderator();
    }
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
  
  dalete(moderator:Moderator) {
    if (moderator.id) {
      this.service.delete(moderator).subscribe(
        (result) => {
          this.notificationService.success('Item removido com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível deletar o Item!');
        }
      );

    }
  }

  goBack() {
    this.router.navigate(['moderator']);
  }

}