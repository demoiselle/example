import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Moderator } from './moderator.model';

@Injectable()
export class ModeratorService {

  constructor(private http: Http) {
  }

  list(currentPage: number, itemsPerPage: number) {
    let start = (currentPage*itemsPerPage) - (itemsPerPage);
    let end = (currentPage*itemsPerPage) - 1;
    return this.http.get('~main/moderators?range='+start+'-'+end)
      .map(res => res);
  }

  get(id: number) {
    return this.http.get('~main/moderators/' + id)
      .map(res => <Moderator>res.json());
  }

  create(moderator: Moderator) {
    return this.http.post('~main/moderators', moderator);
  }

  update(moderator: Moderator) {
    return this.http.put('~main/moderators/', moderator);
  }

  delete(moderator: Moderator) {
    return this.http.delete('~main/moderators/' + moderator.id);
  }
}
