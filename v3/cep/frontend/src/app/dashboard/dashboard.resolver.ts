import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { DashboardService } from './dashboard.service';

@Injectable()
export class DashboardResolver implements Resolve<any> {

  constructor(private service: DashboardService) { }

  resolve(route: ActivatedRouteSnapshot) {
    return this.service.get(route.params['cep']);
  }
}
