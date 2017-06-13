'use strict';

app.controller('AuthController', ['$scope', '$rootScope', 'AUTH_EVENTS', 'AuthService', '$location', '$routeParams',
    function ($scope, $rootScope, AUTH_EVENTS, AuthService, $location, $routeParams) {

        $scope.credentials = {
            username: '',
            password: ''
        };

        var id = $routeParams.id;
        var path = $location.$$url;

        if (path === '/usuario/senha/' + id) {
            $scope.credentials.username = id;
        }

        function error(data, status) {
            $("[id$='-message']").text("");

            switch (status) {
                case 412:
                case 422:
                    $.each(data, function (i, violation) {
                        $("#" + violation.property + "-message").text(violation.message);
                    });
                    break;
                case 401:
                    $("#message").html("Usuário ou senha inválidos.");
                    break;
            }
        }

        $scope.change = function (credentials) {

            if ($rootScope.currentUser)
                $scope.credentials.username = $rootScope.currentUser.identity;

            if (credentials.password) {

                AuthService.change(credentials).then(function () {
                    $("#message").html("Senha Atualizada");
                },
                        function (response) {
                            error(response.data, response.status);
                        });
            }
        };

        $scope.login = function (credentials) {

            if (credentials.username && credentials.password) {

                AuthService.login(credentials).then(function () {

                    $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);

                },
                        function (response) {
                            error(response.data, response.status);
                        });
            } else {
                $("#message").html("Preencha os campos usuário e senha.");
            }
        };

        $scope.aminesia = function (credentials) {

            if (credentials.username) {
                AuthService.aminesia(credentials).then(function () {
                    
                },
                        function (response) {
                            error(response.data, response.status);
                        });
            }
        };

        $scope.logout = function () {
            $rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
        };

    }]);
