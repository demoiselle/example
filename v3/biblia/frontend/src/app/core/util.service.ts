import { Injectable } from '@angular/core';
// import { Observable } from "rxjs/Observable";
// import { Subject } from "rxjs/Subject";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable()
export class UtilService {
    constructor(protected http: HttpClient) {}

    loadEntityListAsPromise(entityName) {
        const url = environment.apiUrl + `v1/${entityName}?fields=id,description`;
        return new Promise((resolve, reject) => {
            this.http.get(url).subscribe(value => {
                resolve(value);
            });
        });
    }

    loadAllEntityListAsPromise(entityList) {
        const promises = entityList.map(e => this.loadEntityListAsPromise(e));
        return Promise.all(promises);
    }
}
