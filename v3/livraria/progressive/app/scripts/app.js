'use strict';

var app = angular.module('app', [
    'ngAria',
    'ngAnimate',
    'ngResource',
    'ngMessages',
    'ngRoute',
    'ngSanitize',
    'ui.bootstrap',
    'ngWebsocket',
    'ngMaterial',
    'ngMdIcons',
    'ui.gravatar',
    'vcRecaptcha',
    'Config'
]).config(['$routeProvider', 'USER_ROLES', '$websocketProvider',
    function ($routeProvider, USER_ROLES, $websocketProvider) {

        $websocketProvider.$setup({
            lazy: false,
            reconnect: true,
            reconnectInterval: 7777,
            mock: false,
            enqueue: true
        });

        $routeProvider.otherwise({
            redirectTo: '/',
            data: {
                authorizedRoles: [USER_ROLES.USUARIO]
            }
        });

        $routeProvider.when('/403', {
            templateUrl: 'views/login.html',
            controller: 'AuthController',
            data: {
                authorizedRoles: [USER_ROLES.NOT_LOGGED]
            }
        });



    }
]);

app.config(function (vcRecaptchaServiceProvider) {
    vcRecaptchaServiceProvider.setSiteKey('6LcUPCcUAAAAAFkn26q4uinHJeH6vndyQVxLpFIK');
    vcRecaptchaServiceProvider.setTheme('light');
    vcRecaptchaServiceProvider.setSize('invisible');
    vcRecaptchaServiceProvider.setType('image');
    vcRecaptchaServiceProvider.setLang('pt-BR');
});


app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.useApplyAsync(true);
        $httpProvider.interceptors.push(['$q', '$rootScope', 'AppService', 'ENV', function ($q, $rootScope, AppService, ENV) {
                return {
                    'request': function (config) {
                        $rootScope.$broadcast('loading-started');
                        var token = AppService.getToken();
                        if (config.url.indexOf("http") === -1) {
                            if (config.url.indexOf("api") !== -1) {
                                config.url = ENV.apiEndpoint + config.url;
                            }
                        }

                        if (token) {
                            config.headers['Authorization'] = 'JWT ' + token;
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
app.run(['$rootScope', '$location', '$window', 'AUTH_EVENTS', 'APP_EVENTS', 'USER_ROLES', 'AuthService', 'AppService', 'AlertService', 'WS',
    function ($rootScope, $location, $window, AUTH_EVENTS, APP_EVENTS, USER_ROLES, AuthService, AppService, AlertService, WS) {

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

        $rootScope.$on(AUTH_EVENTS.quantidade, function (emit, args) {
            $rootScope.$apply(function () {
                $rootScope.conectados = args.emit.data;
            });
        });

        $rootScope.$on(AUTH_EVENTS.lista, function (emit, args) {
            $rootScope.$apply(function () {
                $rootScope.lista = JSON.parse(args.emit.data);
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
            WS.command("logout", $rootScope.currentUser.identity);
            WS.command("lista", $rootScope.currentUser.identity);
            $rootScope.currentUser = null;
            AppService.removeToken();
            $location.path('/login');
        });
        $rootScope.$on(AUTH_EVENTS.loginSuccess, function () {
            WS.command("login", $rootScope.currentUser.identity);
            $window.location.reload();
            $location.path('/agenda');
        });
        $rootScope.$on(APP_EVENTS.offline, function () {
            AlertService.clear();
            AlertService.addWithTimeout('danger', 'Servidor está temporariamente indisponível, tente mais tarde');
        });
        // Check if a new cache is available on page load.
        $window.addEventListener('load', function (e) {
            $window.applicationCache.addEventListener('updateready', function (e) {
                if ($window.applicationCache.status === $window.applicationCache.UPDATEREADY) {
                    $window.location.reload();
                    //$rootScope.$apply();
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
    exit: 'exit',
    push: 'push',
    sistema: 'sistema',
    quantidade: 'count',
    lista: 'list'
});
app.constant('USER_ROLES', {
    ADMINISTRADOR: 'ADMINISTRADOR',
    GERENTE: 'GERENTE',
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
                    412: APP_EVENTS.validate,
                    401: AUTH_EVENTS.notAuthenticated,
                    419: AUTH_EVENTS.sessionTimeout,
                    440: AUTH_EVENTS.sessionTimeout
                }[response.status], response);
                return $q.reject(response);
            }
        };
    }]);