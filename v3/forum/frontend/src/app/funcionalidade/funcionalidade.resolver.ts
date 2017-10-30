import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { FuncionalidadeService } from './funcionalidade.service';

@Injectable()
export class FuncionalidadeResolver implements Resolve<any> {

  constructor(private service: FuncionalidadeService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}