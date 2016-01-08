'use strict';

app.factory('AuthService', ['$http', 'AppService', '$rootScope',
    function($http, AppService, $rootScope) {

        var authService = {};

        authService.login = function(credentials) {

            AppService.removeToken();

            return $http
                .post('api/auth', credentials)
                .success(function(res, status, headers) {

                    AppService.setToken(headers('Set-Token'));

                    return res;
                }
                );

        };

        authService.setCss = function(css) {
            AppService.setCss(css);
        };

        authService.getCss = function() {
            return AppService.getCss();
        };

        function getRoles(grupos) {
            var roles = [];

            if (grupos) {
                for (var i = 0; i < grupos.length; i++) {
                    for (var j = 0; j < grupos[i].perfis.length; j++) {
                        if (roles.indexOf(grupos[i].perfis[j]) == -1) {
                            roles.push(grupos[i].perfis[j]);
                        }
                    }
                }
            }

            return roles;
        }

        authService.logout = function() {

            return $http
                .delete('api/auth')
                .then(function(response) {
                    console.log('AuthService Logout Success');
                    AppService.removeToken();
                }
                );
        };

        authService.isAuthenticated = function() {
            return $rootScope.currentUser ? true : false;
        };

        authService.isAuthorized = function(authorizedRoles) {

            if (authService.isAuthenticated()) {

                if (!angular.isArray(authorizedRoles)) {
                    authorizedRoles = [authorizedRoles];
                }

                var hasAuthorizedRole = false;

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
            }
            else {
                return false;
            }

            return hasAuthorizedRole;
        };

        return authService;
    }]);

