'use strict';

app.controller('ApplicationController', ['$rootScope', 'USER_ROLES', 'AuthService', 'LAYOUTS', '$notification',
    function ($rootScope, USER_ROLES, AuthService, LAYOUTS, $notification) {

        $rootScope.userRoles = USER_ROLES;
        $rootScope.isAuthorized = AuthService.isAuthorized;
        $rootScope.bootstraps = LAYOUTS;
        $rootScope.logados = '0';

        // set the default bootswatch name
        $rootScope.css = AuthService.getCss();

        $rootScope.setCss = function (css) {
            AuthService.setCss(css);
        };

        $rootScope.$on("websocket", function (emit, args) {
            $notification(args.emit.event, {
                body: args.emit.data
            });
        });

//        $notification('Estacionamento', {
//            body: 'Retire seu carro.',
//            focusWindowOnClick: true
//        });


    }]);
