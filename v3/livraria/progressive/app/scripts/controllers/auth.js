'use strict';

app.controller('AuthController', ['$scope', '$rootScope', 'AUTH_EVENTS', 'AuthService', '$location', '$routeParams', 'AlertService', 'vcRecaptchaService',
    function ($scope, $rootScope, AUTH_EVENTS, AuthService, $location, $routeParams, AlertService, vcRecaptchaService) {

        $scope.credentials = {
            username: '',
            password: ''
        };

        $scope.setWidgetId = function (widgetId) {
            // store the `widgetId` for future usage.
            // For example for getting the response with
            // `recaptcha.getResponse(widgetId)`.
        };

        $scope.setResponse = function (response) {
            // send the `response` to your server for verification.
        };

        $scope.cbExpiration = function () {
            // reset the 'response' object that is on scope
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
