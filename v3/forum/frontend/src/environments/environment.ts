// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/forum/api/',
  socketUrl: 'ws://localhost:8080/forum/push/meu-canal',
  googleId: '547939596694-2r4hd52mojck61ji1r43qhcmh220tpmj.apps.googleusercontent.com',
  facebookId: '112143105621779'
};
