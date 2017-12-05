import { DataService, ExceptionService } from '@demoiselle/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable()
export class TopicoService extends DataService {

  constructor(http: HttpClient, exceptionService: ExceptionService) {
    super(environment.apiUrl + 'v1/topicos', 'topico', http, exceptionService);
  }

}
