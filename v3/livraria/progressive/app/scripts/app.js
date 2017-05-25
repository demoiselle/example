'use strict';

var app = angular.module('app', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngAnimate',
    'ngTouch',
    'ui.bootstrap',
    'ui.grid',
    'ngWebsocket',
    'Config'
]).config(['$websocketProvider',
    function ($websocketProvider) {

        $websocketProvider.$setup({
            reconnect: true,
            reconnectInterval: 777
        });

        Notification.requestPermission().then(function (result) {
            if (result === 'denied') {
                console.log('Permission wasn\'t granted. Allow a retry.');
                return;
            }
            if (result === 'default') {
                console.log('The permission request was dismissed.');
                return;
            }
            // Do something with the granted permission.
        });

        if (!navigator.serviceWorker || !navigator.serviceWorker.register) {
            console.log("This browser doesn't support service workers");
            return;
        }

        navigator.serviceWorker.register("/service-worker.js", {scope: '/'})
                .then(function (registration) {
                    console.log("Service worker registered, scope: " + registration.scope);
                    console.log("Refresh the page to talk to it.");
                    // If we want to, we might do `location.reload();` so that we'd be controlled by it
                })
                .catch(function (error) {
                    console.log("Service worker registration failed: " + error.message);
                });

        if (!('showNotification' in ServiceWorkerRegistration.prototype)) {
            console.log('Notifications aren\'t supported.');
            return;
        }

        if (Notification.permission === 'denied') {
            console.log('The user has blocked notifications.');
            return;
        }

        if (!('PushManager' in window)) {
            console.log('Push messaging isn\'t supported.');
            return;
        }

    }]);

app.config(['$httpProvider', function ($httpProvider) {

        $httpProvider.interceptors.push(['$q', '$rootScope', 'AppService', 'ENV', function ($q, $rootScope, AppService, ENV) {
                return {
                    'request': function (config) {
                        $rootScope.$broadcast('loading-started');

                        var token = AppService.getToken();

                        if (ENV.name === "development") {
                            if (config.url.indexOf("api") !== -1) {
                                config.url = ENV.apiEndpoint + config.url;
                            }
                        }

                        if (token) {
                            config.headers['Authorization'] = "JWT " + token;
                        }

                        return config || $q.when(config);
                    },
                    'response': function (response) {
                        $rootScope.$broadcast('loading-complete');
                        return response || $q.when(response);
                    },
                    'responseError': function (rejection) {
                        $rootScope.$broadcast('loading-complete');
                        return $q.reject(rejection);
                    },
                    'requestError': function (rejection) {
                        $rootScope.$broadcast('loading-complete');
                        return $q.reject(rejection);
                    }
                };
            }]);

        $httpProvider.interceptors.push(['$injector', function ($injector) {
                return $injector.get('AuthInterceptor');
            }]);

    }]);

app.run(['$rootScope', '$location', '$window', 'AUTH_EVENTS', 'APP_EVENTS', 'USER_ROLES', 'AuthService', 'AppService', 'AlertService',
    function ($rootScope, $location, $window, AUTH_EVENTS, APP_EVENTS, USER_ROLES, AuthService, AppService, AlertService) {

        $rootScope.$on('$routeChangeStart', function (event, next) {

            if (next.redirectTo !== '/') {
                var authorizedRoles = next.data.authorizedRoles;

                if (authorizedRoles.indexOf(USER_ROLES.NOT_LOGGED) === -1) {

                    if (!AuthService.isAuthorized(authorizedRoles)) {
                        event.preventDefault();
                        if (AuthService.isAuthenticated()) {
                            // user is not allowed
                            $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                        } else {
                            // user is not logged in
                            $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                        }
                    }
                }
            }
        });


        $rootScope.$on(AUTH_EVENTS.mensagem, function (emit, args) {
            AlertService.notification("Mensagem", args.emit.data);
        });

        $rootScope.$on(AUTH_EVENTS.mensagem, function (emit, args) {
            AlertService.notification("Comunicado", args.emit.data);
        });

        $rootScope.$on(AUTH_EVENTS.quantidade, function (emit, args) {
            $rootScope.$apply(function () {
                $rootScope.conectados = args.emit.data;
            });
        });

        $rootScope.$on(AUTH_EVENTS.notAuthorized, function () {
            $location.path("/403");
        });

        $rootScope.$on(AUTH_EVENTS.notAuthenticated, function () {
            $rootScope.currentUser = null;
            AppService.removeToken();
            $location.path("/login");
        });

        $rootScope.$on(AUTH_EVENTS.loginFailed, function () {
            AppService.removeToken();
            $location.path("/login");
        });

        $rootScope.$on(AUTH_EVENTS.logoutSuccess, function () {
            $rootScope.currentUser = null;
            AppService.removeToken();
            $location.path("/dashboard");
        });

        $rootScope.$on(AUTH_EVENTS.loginSuccess, function () {
            $location.path("/dashboard");
        });

        $rootScope.$on(APP_EVENTS.offline, function () {
            AlertService.clear();
            AlertService.addWithTimeout('danger', 'Servidor esta temporariamente indisponível, tente mais tarde');
        });

        // Check if a new cache is available on page load.
        $window.addEventListener('load', function (e) {
            $window.applicationCache.addEventListener('updateready', function (e) {
                if ($window.applicationCache.status === $window.applicationCache.UPDATEREADY) {
                    // Browser downloaded a new app cache.
                    $window.location.reload();
                    alert('Uma nova versão será carregada!');
                }
            }, false);
        }, false);

    }]);

app.constant('APP_EVENTS', {
    offline: 'app-events-offline'
});

app.constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized',
    comunicado: 'comunicado',
    mensagem: 'mensagem',
    quantidade: 'qtde'
});

app.constant('USER_ROLES', {
    ADMINISTRADOR: 'ADMINISTRADOR',
    FUNCIONARIO: 'FUNCIONARIO',
    USUARIO: 'USUARIO',
    NOT_LOGGED: 'NOT_LOGGED'
});

app.factory('AuthInterceptor', ['$rootScope', '$q', 'AUTH_EVENTS', 'APP_EVENTS',
    function ($rootScope, $q, AUTH_EVENTS, APP_EVENTS) {

        return {
            responseError: function (response) {
                $rootScope.$broadcast({
                    '-1': APP_EVENTS.offline,
                    0: APP_EVENTS.offline,
                    404: APP_EVENTS.offline,
                    503: APP_EVENTS.offline,
                    401: AUTH_EVENTS.notAuthenticated,
                    //403: AUTH_EVENTS.notAuthorized,
                    419: AUTH_EVENTS.sessionTimeout,
                    440: AUTH_EVENTS.sessionTimeout
                }[response.status], response);

                return $q.reject(response);
            }
        };

    }]);





