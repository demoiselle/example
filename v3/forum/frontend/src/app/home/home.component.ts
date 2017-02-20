import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../shared/notification.service';

@Component({
  selector: 'my-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(protected notificationService: NotificationService) {
  }

  ngOnInit() {
    console.log('[HomeComponent] initialized.');
  }
}
