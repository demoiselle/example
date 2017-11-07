import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { Observable } from "rxjs/Observable";

import { Dashboard } from "./dashboard.model";

@Injectable()
export class DashboardService {
  constructor(private http: Http) {}

  get(cep: string) {
    return this.http
      .get("~main/ceps?cep=" + cep)
      .map(res => <Dashboard[]>res.json());
  }
}
