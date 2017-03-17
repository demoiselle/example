import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Guest } from './guest.model';

@Injectable()
export class GuestService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    return this.http.get('~main/guests?range='+start+'-'+end)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/guests/' + id)
      .map(res => <Guest>res.json());
  }

  create(guest: Guest) {
    return this.http.post('~main/guests', guest);
  }

  update(guest: Guest) {
    return this.http.put('~main/guests/', guest);
  }

  delete(guest: Guest) {
    return this.http.delete('~main/guests/' + guest.id);
  }
}
