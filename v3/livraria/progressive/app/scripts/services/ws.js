'use strict';

app.factory('WS', ['$rootScope', '$websocket',
    function ($rootScope, $websocket) {

        var service = {};

        navigator.vibrate = navigator.vibrate || navigator.webkitVibrate || navigator.mozVibrate || navigator.msVibrate;
        if (navigator.vibrate) {
            navigator.vibrate([500, 500, 500, 500, 500]);
        }

        var wsUrl = 'wss://push-fwkdemoiselle.rhcloud.com:8443/push/livraria';

        var ws = $websocket.$new({
            url: wsUrl, protocols: [], subprotocols: ['base46']
        });

        ws.$on('$open', function () {
            console.log('WS ON');
            if ($rootScope.currentUser) {
                ws.$emit('login', $rootScope.currentUser.name);
            }
        });

        ws.$on('$close', function () {
            console.log('WS OFF');
        });

        ws.$on('$error', function (error) {
            console.log(error);
            console.log('WS ERROR');
        });

        ws.$on('$message', function (emit) {
            $rootScope.$broadcast(emit.event, {emit: emit});
        });

        service.command = function (command, mensagem) {
            ws.$emit(command, mensagem);
        };

        return service;

    }]);
