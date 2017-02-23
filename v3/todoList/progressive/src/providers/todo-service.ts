import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
// import { Observable } from 'rxjs/Rx';

import * as moment from 'moment';

import { AuthService } from '@demoiselle/security';

import { Todo } from './todo-model';

/*
  Generated class for the TodoService provider.
*/
@Injectable()
export class TodoService {

  endpoint: string = 'https://todo-fwkdemoiselle.rhcloud.com/api/v1/';

  constructor(protected http: Http, protected authService: AuthService) {
    console.log('Hello TodoService Provider');
  }

  get(id: string) {
    return this.http.get(this.endpoint + 'user/' + id).map(res => <Todo>res.json());
  }

  list(currentPage: number, itemsPerPage: number) {
    console.log('TODO: implementar paginação:', currentPage, itemsPerPage);
    let url = this.endpoint + 'user/' + this.authService.getIdentityFromToken();
    return this.http.get(url).map(res => {
      var json = res.json();
      if (json && json.todos) {
        return <Todo[]>json.todos;
      }

      return [];
    });
  }

  create(todo: Todo) {
    todo.user = {
      id: this.authService.getIdentityFromToken()
    };
    return this.http.post('https://todo-fwkdemoiselle.rhcloud.com/api/v1/todo/', todo).map(res => res.json());
  }

  update(todo: Todo) {
    todo.user = {
      id: this.authService.getIdentityFromToken()
    };
    return this.http.put('https://todo-fwkdemoiselle.rhcloud.com/api/v1/todo/', todo);
  }

  delete(todo: Todo) {
    todo.user = {
      id: this.authService.getIdentityFromToken()
    };
    return this.http.delete('https://todo-fwkdemoiselle.rhcloud.com/api/v1/todo/' + todo.id);
  }

  defer(todo: Todo, days: number) {
    let date = moment(todo.dateEnd, 'YYYY-MM-DD');
    date = date.add(days, 'days');
    todo.dateEnd = date.format('YYYY-MM-DD');
    return this.update(todo);
  }

  archive(todo: Todo) {
    console.log('To-Do arquivado:', todo);
    todo.status = 'archived';
    return this.update(todo);
  }
}
