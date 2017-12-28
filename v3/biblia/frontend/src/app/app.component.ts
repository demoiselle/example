import { Component, ViewContainerRef } from '@angular/core';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ServiceWorkerService } from './core/sw.service';
import { WebSocketService } from './core/websocket.service';

@Component({
  // tslint:disable-next-line
  selector: 'body',
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'app';

  constructor(private serviceWorkerService: ServiceWorkerService, private toastr: ToastsManager, private vcr: ViewContainerRef, private webSocketService: WebSocketService) {
    this.toastr.setRootViewContainerRef(vcr);

    serviceWorkerService.subscribeToPush().then(registration => {
      console.debug({ registration });
    });

    console.debug('[WS] connectando...');
    this.webSocketService.connect()
      .then((wsConnection) => {
        console.info('[WS] conectado.');
      });

    // this.webSocketService.events.subscribe(newEvent => {
    //   console.debug('AppComponent ouvindo eventos do WebSocket:', newEvent);
    // });
  }
}
