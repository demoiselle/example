import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { GuestService } from './guest.service';
import { Guest } from './guest.model';

@Component({
  selector: 'app-guest-edit',
  templateUrl: './guest-edit.component.html'
})
export class GuestEditComponent implements OnInit {
  guest: Guest;
  
  private funcao = 'Criar';

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: GuestService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['guest']) {
      this.guest = this.route.snapshot.data['guest'];
      this.funcao = 'Editar';
    } else {
      this.funcao = 'Criar';
      this.guest = new Guest();
    }
  }


  save(guest:Guest) {
    if (!guest.id) {
      delete guest.id;
      this.service.create(guest).subscribe(
        (result) => {
          this.notificationService.success('Item criado com sucesso!');
          this.goBack();
        },
        (error) => {
          this.notificationService.error('Não foi possível salvar!');
        }
      );
    } else {
      this.service.update(guest).subscribe(
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
  
  dalete(guest:Guest) {
    if (guest.id) {
      this.service.delete(guest).subscribe(
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
    this.router.navigate(['guest']);
  }

}