'use strict';

app.config(['$routeProvider', 'USER_ROLES',
    function ($routeProvider, USER_ROLES) {

        $routeProvider

                .when('/', {
                    templateUrl: 'views/dashboard.html',
                    controller: 'DashboardController',
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