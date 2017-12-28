import { Injectable } from '@angular/core';

const ERROR_CREDENTIAL_NOT_AVAILABLE = new Error('Credentials is not available');

@Injectable()
export class CredentialManagementService {

  constructor() { }

  isCredentialsAvailable() {
    if (navigator['credentials'] && navigator['credentials'].preventSilentAccess) {
      return Promise.resolve(true);
    }

    return Promise.reject(ERROR_CREDENTIAL_NOT_AVAILABLE);
  }

  store(payload): Promise<any> {
    return this.isCredentialsAvailable().then(() => {
      const credential = {
        id: payload.username,
        idName: 'username',
        password: payload.password
      };
      const passwordCredential = new window['PasswordCredential'](credential);
      return navigator['credentials'].store(passwordCredential);
    });
  }

  getCredential(silent): Promise<any> {
    return this.isCredentialsAvailable().then(() => {
      return navigator['credentials'].get({
        password: true,
        mediation: silent ? 'silent' : 'optional'
      });
    });
  }

  autoSignin(): Promise<any> {
    const silent = false;
    return this.getCredential(silent).then(credentials => {
      if (!credentials) {
        return Promise.reject('Nenhum credencial armazenada ainda.');
      }
      return credentials;
    });
  }
}
