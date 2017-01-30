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
    'ngWebsocket',
    'Config'
]).config(['$websocketProvider',
    function ($websocketProvider) {

        $websocketProvider.$setup({
            reconnect: true,
            reconnectInterval: 2000
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

        navigator.serviceWorker.register("/sw.js", {scope: '/cache/'})
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
                            config.headers['Authorization'] = "Token " + token;
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






angular.module('Config', [])

.constant('ENV', {name:'production',apiEndpoint:'https://cep-fwkdemoiselle.rhcloud.com/'})

;
'use strict';

app.controller('DashboardController', ['$scope', 'DashboardService', 'AlertService',
    function ($scope, DashboardService, AlertService) {

        $scope.limpar = function () {
            $scope.logradouro = {};
            $scope.logradouros = [];
            $scope.localidade = {};
            $scope.localidades = [];
            $scope.searchText = "";
            $scope.cep = "";
            $scope.uf = "";
            $scope.loc = "";
            $scope.log = "";
        };

        $scope.limpar();

        $scope.findCep = function () {
            DashboardService.findCep($scope.cep).then(
                    function (data) {
                        $scope.logradouro = data;
                    },
                    function (error) {
                        var data = error[0];
                        var status = error[1];

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', data.message);
                        }
                    }
            );
        };

        $scope.findUf = function () {
            DashboardService.findUf($scope.uf).then(
                    function (data) {
                        $scope.localidades = data;
                    },
                    function (error) {
                        var data = error[0];
                        var status = error[1];

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', data.message);
                        }
                    }
            );
        };

        $scope.findLocalidade = function () {
            DashboardService.findLocalidade($scope.loc).then(
                    function (data) {
                        $scope.localidades = data;
                    },
                    function (error) {
                        var data = error[0];
                        var status = error[1];

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', data.message);
                        }
                    }
            );
        };

        $scope.findLogradouro = function () {
            DashboardService.findLogradouro($scope.log).then(
                    function (data) {
                        $scope.logradouros = data;
                    },
                    function (error) {
                        var data = error[0];
                        var status = error[1];

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', data.message);
                        }
                    }
            );
        };

    }]);

'use strict';


app.directive('uiLinhabar', ['$rootScope', '$anchorScroll', function ($rootScope, $anchorScroll) {
        return {
            restrict: 'AC',
            template: '<span class="bar"></span>',
            link: function (scope, el, attrs) {
                el.addClass('linhabar hide');

                scope.$on('$routeChangeStart', function (e) {
                    $anchorScroll();
                    el.removeClass('hide').addClass('active');
                });

                scope.$on('$routeChangeSuccess', function (event, toState, toParams, fromState) {
                    event.targetScope.$watch('$viewContentLoaded', function () {
                        el.addClass('hide').removeClass('active');
                    });
                });

                scope.$on('loading-started', function (e) {
                    el.removeClass('hide').addClass('active');
                });

                scope.$on('loading-complete', function (e) {
                    el.addClass('hide').removeClass('active');
                });
            }
        }
    }]);

app.directive('backButton', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            element.bind('click', function () {
                history.back();
                scope.$apply();
            });
        }
    };
});

app.directive('alerts', function () {
    return {
        restrict: 'E',
        templateUrl: 'partials/alerts.html'
    };
});

app.directive('autofill', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            scope.$on('autofill:update', function () {
                ngModel.$setViewValue(element.val());
            });
        }
    };
});

app.directive('hasRoles', ['AuthService', function (AuthService) {
        return {
            restrict: 'A',
            link: function (scope, element, attributes) {

                var paramRoles = attributes.hasRoles.split(',');

                if (!AuthService.isAuthorized(paramRoles)) {
                    element.remove();
                }
            }
        };
    }]);

app.directive('isLogged', ['AuthService', function (AuthService) {
        return {
            restrict: 'A',
            link: function (scope, element, attributes) {
                if (!AuthService.isAuthenticated()) {
                    element.remove();
                }
            }
        };
    }]);

app.directive('maxLength', ['$compile', 'AlertService', function ($compile, AlertService) {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, elem, attrs, ctrl) {
                attrs.$set('ngTrim', 'false');
                var maxlength = parseInt(attrs.maxLength, 10);
                ctrl.$parsers.push(function (value) {
                    if (value !== undefined && value.length !== undefined) {
                        if (value.length > maxlength) {
                            AlertService.addWithTimeout('warning', 'O valor máximo de caracteres (' + maxlength + ') para esse campo já foi alcançado');
                            value = value.substr(0, maxlength);
                            ctrl.$setViewValue(value);
                            ctrl.$render();
                        }
                    }
                    return value;
                });
            }
        };
    }]);

app.directive('hasRolesDisable', ['AuthService', function (AuthService) {
        return {
            restrict: 'A',
            link: function (scope, element, attributes) {

                var paramRoles = attributes.hasRolesDisable.split(',');

                if (!AuthService.isAuthorized(paramRoles)) {
                    angular.forEach(element.find('input, select, textarea, button, a'), function (node) {
                        var ele = angular.element(node);
                        ele.attr('disabled', 'true');
                    });
                }
            }
        };
    }]);


app.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind('keydown keypress', function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});

'use strict';

app.config(['$routeProvider', 'USER_ROLES',
    function ($routeProvider, USER_ROLES) {

        $routeProvider

                .when('/', {
                    templateUrl: 'views/cep.html',
                    controller: 'DashboardController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/cep', {
                    templateUrl: 'views/cep.html',
                    controller: 'DashboardController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/uf', {
                    templateUrl: 'views/uf.html',
                    controller: 'DashboardController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/logradouro', {
                    templateUrl: 'views/logradouro.html',
                    controller: 'DashboardController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .otherwise({
                    redirectTo: '/',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                });

    }]);
'use strict';

app.factory('AlertService', ['$rootScope', '$timeout',
    function ($rootScope, $timeout) {
        var alertService = {};

        // create an array of alerts available globally
        $rootScope.alerts = [];

        alertService.addWithTimeout = function (type, msg, timeout) {
            var alert = alertService.add(type, msg);
            $timeout(function () {
                alertService.closeAlert(alert);
            }, timeout ? timeout : 4000);
        };

        alertService.add = function (type, msg, timeout) {
            if (type && msg) {
                $rootScope.alerts.push({
                    'type': type,
                    'msg': msg
                });
            }
        };

        alertService.showMessageForbiden = function () {
            this.addWithTimeout('danger', 'Você não tem permissão para executar essa operação');
        };

        alertService.closeAlert = function (alert) {
            return this.closeAlertIdx($rootScope.alerts.indexOf(alert));
        };

        alertService.closeAlertIdx = function (index) {
            return $rootScope.alerts.splice(index, 1);
        };

        alertService.clear = function () {
            $rootScope.alerts = [];
        };

        alertService.notification = function (message) {
            return new Promise(function (resolve, reject) {
                var messageChannel = new MessageChannel();
                messageChannel.port1.onmessage = function (event) {
                    if (event.data.error) {
                        reject(event.data.error);
                    } else {
                        resolve(event.data);
                    }
                };
                navigator.serviceWorker.controller.postMessage(message, [messageChannel.port2]);
            });
        };


        return alertService;
    }]);
'use strict';

app.factory('AppService', ['$window', '$rootScope', function ($window, $rootScope) {

        var tokenKey = "token";

        var service = {};

        service.getToken = function () {

            var token = $window.localStorage.getItem(tokenKey);
            if (token && token !== undefined && token !== null && token !== "null") {
                if (!$rootScope.currentUser) {
                    $rootScope.currentUser = service.getUserFromToken();
                }
                return token;
            } else {
                $window.localStorage.removeItem(tokenKey);
                $rootScope.currentUser = null;
            }
            return null;
        };

        service.setToken = function (token) {
            $window.localStorage.setItem(tokenKey, token);
        };

        service.removeToken = function () {
            $window.localStorage.removeItem(tokenKey);
        };

        service.getUserFromToken = function () {
            var token = $window.localStorage.getItem(tokenKey);

            var user = null;

            if (token !== null && typeof token !== undefined) {
                var encoded = token.split('.')[1];
                var dados = JSON.parse(urlBase64Decode(encoded));
                user = JSON.parse(dados.user);
            }

            return user;
        }

        function urlBase64Decode(str) {
            var output = str.replace('-', '+').replace('_', '/');
            switch (output.length % 4) {
                case 0:
                    break;
                case 2:
                    output += '==';
                    break;
                case 3:
                    output += '=';
                    break;
                default:
                    throw 'Illegal base64url string!';
            }
            return window.atob(output);
        }

        return service;
    }]);


'use strict';

app.factory('AuthService', ['$http', 'AppService', '$rootScope', 'WS',
    function ($http, AppService, $rootScope, WS) {

        var authService = {};

        authService.login = function (credentials) {

            AppService.removeToken();

            return $http
                    .post('api/auth', credentials)
                    .success(function (res, status, headers) {

                        AppService.setToken(headers('Set-Token'));
                        $rootScope.currentUser = AppService.getUserFromToken();

                        return res;
                    }
                    );
        };

        authService.setCss = function (css) {
            AppService.setCss(css);
        };

        authService.getCss = function () {
            return AppService.getCss();
        };

        authService.isAuthenticated = function () {
            return $rootScope.currentUser ? true : false;
        };

        authService.isAuthorized = function (authorizedRoles) {

            var hasAuthorizedRole = false;

            if (authService.isAuthenticated()) {

                if (!angular.isArray(authorizedRoles)) {
                    authorizedRoles = [authorizedRoles];
                }

                var grupos = $rootScope.currentUser.grupos;

                if (grupos !== undefined && grupos !== null) {
                    for (var i = 0; i < authorizedRoles.length; i++) {
                        for (var j = 0; j < grupos.length; j++) {
                            for (var k = 0; k < grupos[j].perfis.length; k++) {
                                if (authorizedRoles[i].indexOf(grupos[j].perfis[k]) !== -1) {
                                    hasAuthorizedRole = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                return false;
            }

            return hasAuthorizedRole;
        };

        return authService;
    }]);


'use strict';

app.factory('DashboardService', ['$http', function ($http) {
        var service = {};

        service.findCep = function (cep) {
            return $http
                    .get('api/cep/' + cep)
                    .then(function (res) {
                        return res.data;
                    });
        };

        service.findUf = function (uf) {
            return $http
                    .get('api/localidade/uf/' + uf)
                    .then(function (res) {
                        return res.data;
                    });
        };

        service.findLocalidade = function (loc) {
            return $http
                    .get('api/localidade/' + loc)
                    .then(function (res) {
                        return res.data;
                    });
        };

        service.findLogradouro = function (log) {
            return $http
                    .get('api/logradouro/' + log)
                    .then(function (res) {
                        return res.data;
                    });
        };

        return service;
    }]);


'use strict';

app.factory('WS', ['$rootScope', '$websocket',
    function ($rootScope, $websocket) {

        var service = {};

        navigator.vibrate = navigator.vibrate || navigator.webkitVibrate || navigator.mozVibrate || navigator.msVibrate;
        if (navigator.vibrate) {
            navigator.vibrate([500]);
        }

        var wsUrl = 'wss://push-fwkdemoiselle.rhcloud.com:8443/push/cep'

//        if (window.location.protocol === 'https:') {
//            wsUrl = 'wss://' + window.location.host + '/ws/echo';
//        } else {
//            wsUrl = 'ws://' + window.location.host + '/ws/echo';
//        }

        var ws = $websocket.$new({
            url: wsUrl, protocols: [], subprotocols: ['base46']
        });

        ws.$on('$open', function () {
            console.log('WS ON');
            if ($rootScope.currentUser) {
                ws.$emit('login', $rootScope.currentUser.name);
            }
        });

        ws.$on('$close', function () {
            console.log('WS OFF');
        });

        ws.$on('$error', function (error) {
            console.log(error);
            console.log('WS ERROR');
        });

        ws.$on('$message', function (emit) {
            $rootScope.$broadcast(emit.event, {emit: emit});
        });

        service.command = function (command, mensagem) {
            ws.$emit(command, mensagem);
        };

        return service;

    }]);
