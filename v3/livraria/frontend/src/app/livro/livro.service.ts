import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Livro } from './livro.model';

@Injectable()
export class LivroService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    return this.http.get('~main/livros?range='+start+'-'+end)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/livros/' + id)
      .map(res => <Livro>res.json());
  }

  create(livro: Livro) {
    return this.http.post('~main/livros', livro);
  }

  update(livro: Livro) {
    return this.http.put('~main/livros/', livro);
  }

  delete(livro: Livro) {
    return this.http.delete('~main/livros/' + livro.id);
  }
}
