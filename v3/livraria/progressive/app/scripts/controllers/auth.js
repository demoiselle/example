'use strict';

app.controller('AuthController', ['$scope', '$rootScope', 'AUTH_EVENTS', 'AuthService', '$location', '$routeParams', 'AlertService',
    function ($scope, $rootScope, AUTH_EVENTS, AuthService, $location, $routeParams, AlertService) {

        $scope.credentials = {
            username: '',
            password: ''
        };

        var id = $routeParams.id;
        var path = $location.$$url;

        if (path === '/usuario/senha/' + id) {
            $scope.credentials.username = id;
        }

        $scope.change = function (credentials) {

            if ($rootScope.currentUser)
                $scope.credentials.username = $rootScope.currentUser.identity;

            if (credentials.password) {
                AuthService.change(credentials).then(function () {
                    $("#message").html("Senha Atualizada");
                });
            }
        };

        $scope.login = function (credentials) {
            if (credentials.username && credentials.password) {
                AuthService.login(credentials).then(
                        function (res) {
                            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                        },
                        function (res) {
                            AlertService.addWithTimeout("Usuário e senha inválidos");
                        }
                );
            } else {
                $("#message").html("Preencha os campos usuário e senha.");
            }


        };

        $scope.register = function (credentials) {

            if (credentials.username) {
                AuthService.register(credentials).then(function () {
                });
            }
        };

        $scope.logout = function () {
            $rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
        };

    }]);
