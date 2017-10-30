import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Funcionalidade } from './funcionalidade.model';

@Injectable()
export class FuncionalidadeService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number, filter: string, field: string = null, desc: boolean = false) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    let orderQuery = '';
    if (field) {
      orderQuery = '&sort='+field+(desc?'&desc':'');
    }
    return this.http.get('~main/funcionalidades?range='+start+'-'+end+filter+orderQuery)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/funcionalidades/' + id)
      .map(res => <Funcionalidade>res.json());
  }

  create(funcionalidade: Funcionalidade) {
    return this.http.post('~main/funcionalidades', funcionalidade);
  }

  update(funcionalidade: Funcionalidade) {
    return this.http.put('~main/funcionalidades/', funcionalidade);
  }

  delete(funcionalidade: Funcionalidade) {
    return this.http.delete('~main/funcionalidades/' + funcionalidade.id);
  }
}
