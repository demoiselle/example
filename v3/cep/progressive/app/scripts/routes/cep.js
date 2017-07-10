'use strict';

app.config(['$routeProvider', 'USER_ROLES',
    function ($routeProvider, USER_ROLES) {

        $routeProvider

                .when('/', {
                    templateUrl: 'views/cep.html',
                    controller: 'CepController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/uf', {
                    templateUrl: 'views/uf.html',
                    controller: 'CepController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/logradouro', {
                    templateUrl: 'views/logradouro.html',
                    controller: 'CepController',
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