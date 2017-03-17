import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';

import { NotificationService } from '../shared';
import { ModeratorService } from './moderator.service';
import { Moderator } from './moderator.model';

@Component({
  selector: 'my-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss']

})
export class ModeratorComponent implements OnInit {
  moderator: Moderator;
  moderators: Moderator[];

  @ViewChild('staticModal') public staticModal: ModalDirective;

  public itemsPerPage: number = 10;
  public totalItems: number = 0;
  public currentPage: number = 1;

  constructor(private service: ModeratorService, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.list();
  }

  showModalDetails(moderator: Moderator) {
    this.moderator = moderator;
    this.staticModal.show();
  }

  pageChanged(event: any): void {
    this.currentPage = event.page;
    this.list();
  }

  list() {
    this.service.list(this.currentPage, this.itemsPerPage).subscribe(
      (result) => {
        try {
          this.moderators = result.json();
        } catch (e) {
          console.log('Can not convert result to JSON.');
          console.log(e);
          this.moderators = [];
        }
        let contentRange = result.headers.get('Content-Range');
        if (contentRange) {
          this.totalItems = Number(contentRange.substr(contentRange.indexOf('/')+1, contentRange.length));
        }
      },
      (error) => {
        this.notificationService.error('Não foi possível carregar a lista de itens!');
        this.moderators = error;
      }
    );
  }

  edit(moderator:Moderator) {
    this.service.update(moderator).subscribe(
      (result) => {
        this.notificationService.success('Item atualizado com sucesso!');
      },
      (error) => {
        this.notificationService.error('Não foi possível salvar!');
      }
    );
  }

  delete(moderator: Moderator) {
    this.service.delete(moderator).subscribe(
      (result) => {
        this.moderator = null;
        this.staticModal.hide();
        this.list();
      },
      (error) => {
        this.notificationService.error('Não foi possível remover!');
      }
    );
  }
}
