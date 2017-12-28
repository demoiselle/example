import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { environment } from '../../environments/environment';

interface WSResult {
  send: Function;
}

@Injectable()
export class WebSocketService {
  private ws: WebSocket;
  private wsSource = new Subject<any>();
  public events = this.wsSource.asObservable();
  private wsConnection: WSResult = null;

  public connect(socketUrl = environment.socketUrl): Promise<WSResult> {
    return new Promise((resolve, reject) => {
      // check if the browser support WebSocket
      const support = window && 'WebSocket' in window;
      if (!support) {
        return reject(new Error('Not support for WebSocket.'));
      }

      if (this.wsConnection && this.ws.readyState === this.ws.OPEN) {
        return resolve(this.wsConnection);
      }

      // create ws
      if (!this.ws) {
        this.ws = new WebSocket(socketUrl);

        // this.ws.onopen = this.onOpen.bind(this);
        this.ws.onclose = this.onClose.bind(this);
        this.ws.onmessage = this.onMessage.bind(this);
        this.ws.onerror = this.onError.bind(this);
      }

      // create result instance
      this.wsConnection = {
        send: (data) => {
          return this.ws.send(JSON.stringify(data));
        },
      };

      this.ws.onopen = () => {
        // console.debug('[WS] onopened.')
        resolve(this.wsConnection);
      };

    });
  }

  private onClose(event) {
    // console.debug('[WS] onclose:', event);
    this.wsSource.next(event);
  }

  private onMessage(event) {
    // console.debug('[WS] onmessage:', event);
    this.wsSource.next(event);
  }

  private onError(event) {
    // console.debug('[WS] onerror:', event);
    this.wsSource.next(event);
  }
}
