import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { CategoriaService } from './categoria.service';

@Injectable()
export class CategoriaResolver implements Resolve<any> {

  constructor(private service: CategoriaService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}