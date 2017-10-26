import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { Router, ActivatedRoute } from '@angular/router';
import { NotificationService } from '../shared';
import { User } from './user.model';
import { UserService } from './user.service';

@Component({
  selector: 'gdt-user-view',
  templateUrl: './user-view.component.html'
})
export class UserViewComponent implements OnInit {
  user: User = new User();

  @ViewChild('staticModalDelete') public staticModalDelete: ModalDirective;

  private routeSubscribe: any;

  constructor(
    private service: UserService,
    private notificationService: NotificationService,
    private route: ActivatedRoute,
    private router: Router)
  { }

  ngOnInit() {
    if (this.route.snapshot.data['user']) {
      this.user = this.route.snapshot.data['user'];
    }
  }

  goBack() {
    this.router.navigate(['user']);
  }

  delete(user: User) {
    this.service.delete(user).subscribe(
      (result) => {
        this.user = null;
        this.staticModalDelete.hide();
        this.goBack();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }

  showModalDelete(user: User) {
    this.user = user;
    this.staticModalDelete.show();
  }

  hideStaticModals() {
    this.staticModalDelete.hide();
  }

}
