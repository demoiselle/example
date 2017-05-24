import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { User } from './user.model';

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    return this.http.get('~main/users?range='+start+'-'+end)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/users/' + id)
      .map(res => <User>res.json());
  }

  create(user: User) {
    return this.http.post('~main/users', user);
  }

  update(user: User) {
    return this.http.put('~main/users/', user);
  }

  delete(user: User) {
    return this.http.delete('~main/users/' + user.id);
  }
}
