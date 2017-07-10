'use strict';

app.factory('WS', ['$rootScope', '$websocket',
    function ($rootScope, $websocket) {

        var service = {};

        navigator.vibrate = navigator.vibrate || navigator.webkitVibrate || navigator.mozVibrate || navigator.msVibrate;
        if (navigator.vibrate) {
            navigator.vibrate([500, 500, 500, 500, 500]);
        }

//        Notification.requestPermission().then(function (result) {
//            if (result === 'denied') {
//                console.log('Permission wasn\'t granted. Allow a retry.');
//                return;
//            }
//            if (result === 'default') {
//                console.log('The permission request was dismissed.');
//                return;
//            }
//            // Do something with the granted permission.
//        });
//
//        if (!navigator.serviceWorker || !navigator.serviceWorker.register) {
//            console.log("This browser doesn't support service workers");
//            return;
//        }
//
//        navigator.serviceWorker.register("/service-worker.js", {scope: '/'})
//                .then(function (registration) {
//                    console.log("Service worker registered, scope: " + registration.scope);
//                    console.log("Refresh the page to talk to it.");
//                    // If we want to, we might do `location.reload();` so that we'd be controlled by it
//                })
//                .catch(function (error) {
//                    console.log("Service worker registration failed: " + error.message);
//                });
//
//        if (!('showNotification' in ServiceWorkerRegistration.prototype)) {
//            console.log('Notifications aren\'t supported.');
//            return;
//        }
//
//        if (Notification.permission === 'denied') {
//            console.log('The user has blocked notifications.');
//            return;
//        }
//
//        if (!('PushManager' in window)) {
//            console.log('Push messaging isn\'t supported.');
//            return;
//        }

        var wsUrl = 'wss://push-fwkdemoiselle.rhcloud.com:8443/push/cep';


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
