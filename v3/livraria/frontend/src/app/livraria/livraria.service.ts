import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Livraria } from './livraria.model';

@Injectable()
export class LivrariaService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    return this.http.get('~main/livrarias?range='+start+'-'+end)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/livrarias/' + id)
      .map(res => <Livraria>res.json());
  }

  create(livraria: Livraria) {
    return this.http.post('~main/livrarias', livraria);
  }

  update(livraria: Livraria) {
    return this.http.put('~main/livrarias/', livraria);
  }

  delete(livraria: Livraria) {
    return this.http.delete('~main/livrarias/' + livraria.id);
  }
}
