import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { NotificationService } from '../shared';
import { LoginService } from '../login/login.service';
import { GuestService } from './guest.service';
import { Guest } from './guest.model';

@Component({
  selector: 'my-guest-edit',
  templateUrl: './guest-edit.component.html'
})
export class GuestEditComponent implements OnInit {
  guest: Guest = new Guest();
  id: number;
  guestLoaded: boolean = false;

  private routeSubscribe: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: GuestService,
    private loginService: LoginService,
    private notificationService: NotificationService)
  { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (Object.keys(params).length > 0) {
        this.guest.id = (<Guest> params).id;
        this.guest.description = (<Guest> params).description;
      }
    });
  }

  loadGuest() {
    this.service.get(this.id)
      .subscribe(
      (Guest: Guest) => {
        this.guest = Guest;
        this.guestLoaded = true;
      },
      error => {
        this.notificationService.error('Erro ao carregar item!');
      }
      );
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
  
  goBack() {
    this.router.navigate(['guest']);
  }

}
