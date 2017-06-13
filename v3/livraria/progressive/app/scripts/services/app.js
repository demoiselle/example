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

            var user = {};

            if (token !== null && typeof token !== undefined) {
                var encoded = token.split('.')[1];
                var dados = JSON.parse(urlBase64Decode(encoded));
                user.identity = dados.identity;
                user.name = dados.name;
                user.roles = dados.roles;
                user.permissions = dados.permissions;
                user.params = dados.params;
            }

            return user;
        };

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
        ;

        return service;
    }]);
