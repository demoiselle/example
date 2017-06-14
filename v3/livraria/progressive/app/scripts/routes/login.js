'use strict';

app.config(['$routeProvider', 'USER_ROLES',
    function ($routeProvider, USER_ROLES) {

        $routeProvider

                .when('/login', {
                    templateUrl: 'views/login.html',
                    controller: 'AuthController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/register', {
                    templateUrl: 'views/register.html',
                    controller: 'AuthController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                });

    }]);