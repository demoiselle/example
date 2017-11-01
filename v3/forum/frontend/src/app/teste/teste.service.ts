import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Teste } from './teste.model';

@Injectable()
export class TesteService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number, filter: string, field: string = null, desc: boolean = false) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    let orderQuery = '';
    if (field) {
      orderQuery = '&sort='+field+(desc?'&desc':'');
    }
    return this.http.get('~main/testes?range='+start+'-'+end+filter+orderQuery)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/testes/' + id)
      .map(res => <Teste>res.json());
  }

  create(teste: Teste) {
    return this.http.post('~main/testes', teste);
  }

  update(teste: Teste) {
    return this.http.put('~main/testes/', teste);
  }

  delete(teste: Teste) {
    return this.http.delete('~main/testes/' + teste.id);
  }
}
