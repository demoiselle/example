import { Injectable } from '@angular/core';
import { NgServiceWorker } from '@angular/service-worker';
import { NgPushRegistration } from '@angular/service-worker/companion/comm';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from '../../environments/environment';

@Injectable()
export class ServiceWorkerService {
  private registration: NgPushRegistration;
  private permission;

  constructor(private sw: NgServiceWorker, private http: HttpClient) { }

  getPermission() {
    if ('serviceWorker' in navigator) {
      Notification.requestPermission(permission => {
        // If the user accepts, let's create a notification
        // console.debug({ permission });
        this.permission = permission;

        if (permission === 'denied') {
          console.warn('Permission for notifications was denied')
        }
        return permission;
      });
      this.sw.push.subscribe(pushObj => {
        console.debug('got push message', pushObj);
      });
    } else {
      console.warn('Browser do not support Service Worker.');
    }
  }

  getFingerprint(): Promise<string> {
    return this.subscribeToPush().then((registration) => {
      // console.log({ registration });
      const url = registration.url;
      if (!url) {
        console.debug('Wrong registration structure?', registration);
        throw new Error('Wrong registration structure?');
      }

      return url;
    });
  }

  sendFingerprint(fingerprint): Observable<Object> {
    const url = environment.apiUrl + 'auth/fingerprint';
    const data = fingerprint;
    return this.http.post(url, data);
  }

  subscribeToPush(): Promise<NgPushRegistration> {
    return new Promise((resolve, reject) => {
      if (this.registration) {
        return resolve(this.registration);
      }

      const subs = this.sw.registerForPush({
        // XXX: será usado o FCM por enquanto e não precisamos declarar o "applicationServerKey".
        // applicationServerKey: environment.applicationServerKey,
      })
        .subscribe((r: NgPushRegistration) => {
          console.debug('successfully registered', r);

          this.registration = r;
          resolve(this.registration)
        },
        err => {
          console.error('error registering for push', err);
          reject(err);
        }, () => {
          console.debug('completed');
          subs.unsubscribe();
        });
    });
  }

  unsubscribeFromPush() {
    this.registration.unsubscribe().subscribe(success => {
      console.debug('unsubscribed', success);
    })
  }

  forceUpdate(): void {
    this.sw.updates.subscribe(u => {
      console.debug('update event', u);

      // Immediately activate pending update
      if (u.type == 'pending') {
        this.sw.activateUpdate(u.version).subscribe(e => {
          console.debug('updated', e);
          alert("App updated! Reload App!");
          // location.reload();
        });
      }
    });

    this.sw.checkForUpdate();
  }

  pingCompanion(): void {
    this
      .sw
      .ping()
      .subscribe(undefined, undefined, () => {
        console.debug('pong');
      });

  }

  registerForPush(): void {
    this
      .sw
      .push
      .map(value => JSON.stringify(value))
      .subscribe(registration => {
        console.debug(registration);
      });
  }

  notify(title, options = {}) {
    if (!("Notification" in window)) {
      throw new Error("This browser does not support system notifications");
    }

    if (this.permission === "granted") {
      // If it's okay let's create a notification
      return new Notification(title, options);
    }
  }
}
