import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class CepService {

  constructor(private http: Http) {
  }

  getUF() {
    return this.http.get('api/uf')
      .map(res => res.json());
  }

  getLocalidadeByUF(uf: string) {
    return this.http.get('localidade/uf/' + uf)
      .map(res => res.json());
  }

  getByCEP(cep: string) {
    return this.http.get('api/cep?cep=' + cep)
      .map(res => res.json()[0]);
  }

  getByUF(uf: string) {
    return this.http.get('api/localidade/uf/' + uf)
      .map(res => res.json()[0]);
  }

  getByLocalidade(loc: string) {
    return this.http.get('api/localidade/' + loc)
      .map(res => res.json()[0]);
  }

  getByLogradouro(log: string) {
    return this.http.get('api/logradouro/' + log)
      .map(res => res.json()[0]);
  }
}
