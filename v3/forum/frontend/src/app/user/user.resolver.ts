import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { UserService } from './user.service';

@Injectable()
export class UserResolver implements Resolve<any> {

  constructor(private service: UserService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}