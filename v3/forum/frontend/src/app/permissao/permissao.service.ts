import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Permissao } from './permissao.model';

@Injectable()
export class PermissaoService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number, filter: string, field: string = null, desc: boolean = false) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    let orderQuery = '';
    if (field) {
      orderQuery = '&sort='+field+(desc?'&desc':'');
    }
    return this.http.get('~main/permissaos?range='+start+'-'+end+filter+orderQuery)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/permissaos/' + id)
      .map(res => <Permissao>res.json());
  }

  create(permissao: Permissao) {
    return this.http.post('~main/permissaos', permissao);
  }

  update(permissao: Permissao) {
    return this.http.put('~main/permissaos/', permissao);
  }

  delete(permissao: Permissao) {
    return this.http.delete('~main/permissaos/' + permissao.id);
  }
}
