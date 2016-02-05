'use strict';

app.factory('WS', ['$rootScope', '$websocket', '$timeout',
    function ($rootScope, $websocket, $timeout) {

        var service = {};

        navigator.vibrate = navigator.vibrate || navigator.webkitVibrate || navigator.mozVibrate || navigator.msVibrate;
        var wsUrl;

        if (window.location.protocol == 'https:') {
            wsUrl = 'wss://' + window.location.host + ':8443/ws/echo';
        } else {
            //wsUrl = 'ws://' + window.location.host + ':8000/ws/echo';
            wsUrl = 'ws://localhost:9090/ws/echo';
        }

        var ws = $websocket.$new({
            url: wsUrl, protocols: [], subprotocols: ['base46']
        });

        ws.$on('$open', function () {
            ws.$emit("qtde", "qtde");
            $rootScope.conectado = true;
            console.log("WS ON");
        });

        ws.$on('$close', function () {
            ws.$emit("qtde", "qtde");
            $rootScope.conectado = false;
            console.log("WS OFF");
        });

        ws.$on('$error', function () {
            $rootScope.conectado = false;
            console.log("WS ERROR");
        });

        ws.$on('$message', function (emit) {
            $rootScope.$broadcast("websocket", {emit:emit});

            if (navigator.vibrate) {
                navigator.vibrate([1000, 500, 1000, 500, 2000]);
            }

        });

        service.mensagem = function (mensagem) {
            ws.$emit("msg", mensagem);
        };

        service.command = function (command, mensagem) {
            ws.$emit(command, mensagem);
        };


        return service;

    }]);
