'use strict';

app.controller('AuthController', ['$scope', '$rootScope', 'AUTH_EVENTS', 'AuthService', '$location', '$routeParams', 'AlertService', 'vcRecaptchaService',
    function ($scope, $rootScope, AUTH_EVENTS, AuthService, $location, $routeParams, AlertService, vcRecaptchaService) {

        $scope.credentials = {
            username: '',
            password: ''
        };

        $scope.response = null;
        $scope.widgetId = null;
        $scope.model = {
            key: '6LcUPCcUAAAAAFkn26q4uinHJeH6vndyQVxLpFIK'
        };
        $scope.setResponse = function (response) {
            console.info('Response available');
            $scope.response = response;
        };
        $scope.setWidgetId = function (widgetId) {
            console.info('Created widget ID: %s', widgetId);
            $scope.widgetId = widgetId;
        };
        $scope.cbExpiration = function () {
            console.info('Captcha expired. Resetting response object');
            vcRecaptchaService.reload($scope.widgetId);
            $scope.response = null;
        };
        $scope.submit = function () {
            var valid;
            /**
             * SERVER SIDE VALIDATION
             *
             * You need to implement your server side validation here.
             * Send the reCaptcha response to the server and use some of the server side APIs to validate it
             * See https://developers.google.com/recaptcha/docs/verify
             */
            console.log('sending the captcha response to the server', $scope.response);
            if (valid) {
                console.log('Success');
            } else {
                console.log('Failed validation');
                // In case of a failed validation you need to reload the captcha
                // because each response can be checked just once
                vcRecaptchaService.reload($scope.widgetId);
            }
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
