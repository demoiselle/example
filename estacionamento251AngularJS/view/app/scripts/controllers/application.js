'use strict';

app.controller('ApplicationController', ['$rootScope', '$notification', '$websocket', '$timeout', 'USER_ROLES', 'AuthService', 'LAYOUTS',
    function ($rootScope, $notification, $websocket, $timeout, USER_ROLES, AuthService, LAYOUTS) {

        $rootScope.placa;

        navigator.vibrate = navigator.vibrate || navigator.webkitVibrate || navigator.mozVibrate || navigator.msVibrate;

        var ws = $websocket.$new({
            url: 'ws://localhost:9090/ws/vaga/SERPRO', protocols: [], subprotocols: ['base46']
                //url: 'ws://estaciona-pgxp.rhcloud.com:8000/ws/vaga/SERPRO', protocols: [], subprotocols: ['base46']
        });

        ws.$on('$open', function () {
            $rootScope.conectado = true;
            console.log("WS ON");
        }).$on('$close', function () {
            $rootScope.conectado = false;
            console.log("WS OFF");
        });

        ws.$on('$message', function (data) {

            $notification('Estacionamento', {
                body: data
            })

            if (navigator.vibrate) {
                navigator.vibrate([1000, 500, 1000, 500, 2000, 1000, 500, 1000, 500, 2000, 1000, 500, 1000, 500, 2000]);
            }

        });

        ws.$on('placa', function (data) {

            $notification('Estacionamento', {
                body: data
            })

            if (navigator.vibrate) {
                navigator.vibrate([1000, 500, 1000, 500, 2000, 1000, 500, 1000, 500, 2000, 1000, 500, 1000, 500, 2000]);
            }

        });

        $rootScope.conectar = function () {
            ws.$emit("placa", $rootScope.placa);
        };


        $rootScope.userRoles = USER_ROLES;
        $rootScope.isAuthorized = AuthService.isAuthorized;
        $rootScope.bootstraps = LAYOUTS;

        // set the default bootswatch name
        $rootScope.css = AuthService.getCss();

        $rootScope.setCss = function (css) {
            AuthService.setCss(css);
        };


//        $notification('Estacionamento', {
//            body: 'Retire seu carro.',
//            focusWindowOnClick: true
//        });


    }]);
