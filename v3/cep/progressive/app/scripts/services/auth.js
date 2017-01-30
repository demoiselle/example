'use strict';

app.factory('AuthService', ['$http', 'AppService', '$rootScope',
    function ($http, AppService, $rootScope) {

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

