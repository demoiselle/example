import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { ModeratorService } from './moderator.service';

@Injectable()
export class ModeratorResolver implements Resolve<any> {

  constructor(private service: ModeratorService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['id']);
  }
}