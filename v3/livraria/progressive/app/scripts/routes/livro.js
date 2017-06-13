'use strict';

app.config(['$routeProvider', 'USER_ROLES', function ($routeProvider, USER_ROLES) {

        $routeProvider

                .when('/livro', {
                    templateUrl: 'views/livro/list.html',
                    controller: 'LivroController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/livro/edit', {
                    templateUrl: 'views/livro/edit.html',
                    controller: 'LivroController',
                    data: {
                        authorizedRoles: [USER_ROLES.NOT_LOGGED]
                    }
                })

                .when('/livro/edit/:id', {
                    templateUrl: 'views/livro/edit.html',
                    controller: 'LivroController',
                    data: {
                        authorizedRoles: [USER_ROLES.ADMINISTRADOR, USER_ROLES.NOT_LOGGED]
                    }
                })

    }]);

