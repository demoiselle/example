import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { PerfilService } from './perfil.service';

@Injectable()
export class PerfilResolver implements Resolve<any> {

  constructor(private service: PerfilService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}