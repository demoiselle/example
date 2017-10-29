import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { GuestService } from './guest.service';

@Injectable()
export class GuestResolver implements Resolve<any> {

  constructor(private service: GuestService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}