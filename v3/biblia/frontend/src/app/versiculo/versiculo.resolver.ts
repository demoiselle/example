import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { VersiculoService } from './versiculo.service';

@Injectable()
export class VersiculoResolver implements Resolve<any> {

  constructor(private service: VersiculoService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.find(route.params['id']);
  }
}
