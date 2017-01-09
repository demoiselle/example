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
    console.log('TODO: implementar paginação:', currentPage, itemsPerPage);

    let url = '~main/user/' + this.authService.getIdentityFromToken();
    return this.http.get(url)
      .map(res => {
        return res.json().todos;
      })
      .catch(function (error) {
        // Mock data
        return Observable.throw(<Todo[]>[{
          id: 1,
          description: 'todo 1 catch'
        }, {
          id: 2,
          description: 'todo 2 catch'
        }
        ]);
      });
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
    return this.http.put('~main/todo', todo);
  }

  delete(todo: Todo) {
    return this.http.delete('~main/todo/' + todo.id);
  }
}
