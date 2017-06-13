'use strict';

app.factory('AuthService', ['$http', 'AppService', '$rootScope', '$interval', '$window', 'AlertService',
    function ($http, AppService, $rootScope, $interval, $window, AlertService) {

        var authService = {};

        authService.change = function (credentials) {

            credentials.fingerprint = 'beta'; //"new Fingerprint({canvas: true}, {screen_resolution: true}).get();"

            return $http
                    .post('api/auth/change', credentials)
                    .success(function (res, status, headers) {
                        AlertService.addWithTimeout('success', res.mensagem);
                        return res;
                    }).error(function (res, status, headers) {
                AlertService.addWithTimeout('warning', "Melhore sua senha");
                return res;
            }
            );

        };

        authService.login = function (credentials) {

            AppService.removeToken();
            credentials.fingerprint = 'beta'; //"new Fingerprint({canvas: true}, {screen_resolution: true}).get();"

            return $http
                    .post('api/auth', credentials)
                    .success(function (res, status, headers) {
                        AppService.setToken(res.key);
                        $rootScope.currentUser = AppService.getUserFromToken();
                        return res;
                    }).error(function (res, status, headers) {
                AlertService.addWithTimeout('warning', "Usuário não identificado");
                return res;
            }
            );

        };

        authService.aminesia = function (credentials) {
            credentials.fingerprint = 'beta'; //"new Fingerprint({canvas: true}, {screen_resolution: true}).get();"

            return $http
                    .post('api/auth/aminesia', credentials)
                    .success(function (res, status, headers) {
                        AlertService.addWithTimeout('warning', res.mensagem);
                        return res;
                    }).error(function (res, status, headers) {
                return res;
            }
            );

        };

        authService.retoken = function () {
            return $http
                    .get('api/auth')
                    .success(function (res, status, headers) {
                        AppService.removeToken();
                        AppService.setToken(res.key);
                        $rootScope.currentUser = AppService.getUserFromToken()
                        $rootScope.$digest();
                        return res;
                    }
                    );
        };

        $interval(function () {
            if ($rootScope.currentUser) {
                authService.retoken();
            }
        }, 6000000);

        authService.logout = function () {
            AppService.removeToken();
        };

        authService.isAuthenticated = function () {
            if (!$rootScope.currentUser) {
                $rootScope.currentUser = AppService.getUserFromToken();

            }

            return $rootScope.currentUser ? true : false;
        };

        authService.isAuthorized = function (authorizedRoles) {

            if (authService.isAuthenticated()) {

                if (!angular.isArray(authorizedRoles)) {
                    authorizedRoles = [authorizedRoles];
                }

                var hasAuthorizedRole = false;

                var perfil = $rootScope.currentUser.roles;

                if (perfil !== undefined && perfil !== null) {
                    for (var i = 0; i < authorizedRoles.length; i++) {
                        for (var p = 0; p < perfil.length; p++) {
                            if (authorizedRoles[i] === perfil[p]) {
                                hasAuthorizedRole = true;
                                break;
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
    }
]);