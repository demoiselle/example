import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { User } from './user.model';

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number) {
    console.log('TODO: implementar paginação:', currentPage, itemsPerPage);

    return this.http.get('~main/user')
      .map(res => res.json())
      .catch((error) => {
        return Observable.throw(<User[]>[{
          id: 1,
          firstName: 'user 1 catch'
        }, {
          id: 2,
          firstName: 'user 2 catch'
        }
        ]);
      });
  }

  get(id: number) {
    return this.http.get('~main/user/' + id)
      .map(res => <User>res.json());
  }

  create(user: User) {
    return this.http.post('~main/user', user);
  }

  update(user: User) {
    return this.http.put('~main/user/' + user.id, user);
  }

  delete(user: User) {
    return this.http.delete('~main/user/' + user.id);
  }

  getPerfil() {
    return this.http.get('~main/constants/perfil')
      .map(res => res.json());
  }

}
