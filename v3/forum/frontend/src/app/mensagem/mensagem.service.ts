import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Mensagem } from './mensagem.model';

@Injectable()
export class MensagemService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    return this.http.get('~main/mensagems?range='+start+'-'+end)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/mensagems/' + id)
      .map(res => <Mensagem>res.json());
  }

  create(mensagem: Mensagem) {
    return this.http.post('~main/mensagems', mensagem);
  }

  update(mensagem: Mensagem) {
    return this.http.put('~main/mensagems/', mensagem);
  }

  delete(mensagem: Mensagem) {
    return this.http.delete('~main/mensagems/' + mensagem.id);
  }
}
