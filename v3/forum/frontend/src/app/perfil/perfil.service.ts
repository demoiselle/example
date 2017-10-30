import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Perfil } from './perfil.model';

@Injectable()
export class PerfilService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number, filter: string, field: string = null, desc: boolean = false) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    let orderQuery = '';
    if (field) {
      orderQuery = '&sort='+field+(desc?'&desc':'');
    }
    return this.http.get('~main/perfils?range='+start+'-'+end+filter+orderQuery)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/perfils/' + id)
      .map(res => <Perfil>res.json());
  }

  create(perfil: Perfil) {
    return this.http.post('~main/perfils', perfil);
  }

  update(perfil: Perfil) {
    return this.http.put('~main/perfils/', perfil);
  }

  delete(perfil: Perfil) {
    return this.http.delete('~main/perfils/' + perfil.id);
  }
}
