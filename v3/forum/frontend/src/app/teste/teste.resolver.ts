import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { TesteService } from './teste.service';

@Injectable()
export class TesteResolver implements Resolve<any> {

  constructor(private service: TesteService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}