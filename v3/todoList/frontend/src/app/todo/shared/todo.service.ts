import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { AuthService } from '@demoiselle/security';
import { Todo } from './todo.model';

@Injectable()
export class TodoService {

  constructor(private http: Http, protected authService: AuthService) {
  }

  list(currentPage: number, itemsPerPage: number) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    let url = '~main/todo';
    return this.http.get(url+'?range='+start+'-'+end)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/todo/' + id)
      .map(res => <Todo>res.json());
  }

  create(todo: Todo) {
    todo.user = {
      id: this.authService.getIdentityFromToken()
    };
    return this.http.post('~main/todo', todo).map(res => res.json());
  }

  update(todo: Todo) {
    todo.user = {
      id: this.authService.getIdentityFromToken()
    };
    return this.http.put('~main/todo', todo);
  }

  delete(todo: Todo) {
    return this.http.delete('~main/todo/' + todo.id);
  }
}
