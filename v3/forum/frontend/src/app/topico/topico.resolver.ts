import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { TopicoService } from './topico.service';

@Injectable()
export class TopicoResolver implements Resolve<any> {

  constructor(private service: TopicoService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.find(route.params['id']);
  }
}
