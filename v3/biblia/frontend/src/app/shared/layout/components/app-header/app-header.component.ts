import { Component, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, TokenService } from '@demoiselle/security';
import { WebSocketService } from '../../../../core/websocket.service';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html'
})
export class AppHeader {

  user;
  connectedUsers = [];
  totalUsers = 0;

  constructor(
    private el: ElementRef,
    private router: Router,
    private authService: AuthService,
    private tokenService: TokenService,
    private webSocketService: WebSocketService) { }

  ngOnInit(): void {
    // wait for the component to render completely
    const nativeElement: HTMLElement = this.el.nativeElement;
    const parentElement: HTMLElement = nativeElement.parentElement;

    // move all children out of the element
    while (nativeElement.firstChild) {
      parentElement.insertBefore(nativeElement.firstChild, nativeElement);
    }

    // remove the empty element(the host)
    parentElement.removeChild(nativeElement);

    this.webSocketService.events.subscribe((wsEvent) => {
      const result = JSON.parse(wsEvent.data || '{}');
      const event = result.event;
      const data = result.data;
      switch (event) {
        case 'count':
          this.handleCountEvent(data);
          break;
        case 'list':
          this.handleListEvent(data);
          break;
        default:
          console.warn('Not implemented yet. "event" == ', event);
          break;
      }
    });

    if (this.isLoggedIn()) {
      this.webSocketService.connect()
        .then((wsConnection) => {
          // console.debug('[WS] conectado.');
          this.user = {
            id: this.tokenService.getIdentityFromToken(),
            name: this.tokenService.getNameFromToken(),
            roles: this.tokenService.getRolesFromToken()
          }
          wsConnection.send({
            event: 'login',
            data: this.user.id
          });
        })
        .catch(error => console.error('Erro ao conectar com WebSocket.', error));
    }
  }

  isLoggedIn() {
    return this.authService.isAuthenticated();
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }

  private handleCountEvent(data) {
    this.totalUsers = data;
  }

  private handleListEvent(data) {
    const users = JSON.parse(data).map(user => {
      const obj = user.split(':');
      let index = 0;
      return {
        name: obj[index++],
        email: obj[index++],
        role: obj[index++],
        // geo: obj[index++],
      };
    });
    this.connectedUsers = users;
    // console.debug({ users });
  }
}
