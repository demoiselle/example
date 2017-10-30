import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { PermissaoService } from './permissao.service';

@Injectable()
export class PermissaoResolver implements Resolve<any> {

  constructor(private service: PermissaoService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}