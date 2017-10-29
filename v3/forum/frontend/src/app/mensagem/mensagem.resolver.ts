import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { MensagemService } from './mensagem.service';

@Injectable()
export class MensagemResolver implements Resolve<any> {

  constructor(private service: MensagemService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}