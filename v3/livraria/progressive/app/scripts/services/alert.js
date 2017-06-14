'use strict';

app.factory('AlertService', ['$rootScope', 'AUTH_EVENTS', 'Win', '$mdToast',
    function ($rootScope, AUTH_EVENTS, Win, $mdToast) {
        var alertService = {};

        alertService.addWithTimeout = function (msg) {
            $mdToast.show(
                    $mdToast.simple()
                    .textContent(msg)
                    .position('top right')
                    .hideDelay(3333)
                    );
        };

        alertService.showMessageForbiden = function () {
            $mdToast.show(
                    $mdToast.simple()
                    .textContent('Você não tem permissão para executar essa operação')
                    .position('top right')
                    .hideDelay(3333)
                    );
        };

        alertService.mobile = function (message) {
            if (!(document.documentMode || /Edge/.test(navigator.userAgent))) {
//                $notification("Mensagem", {
//                    body: message,
//                    dir: 'auto',
//                    delay: 10000,
//                    focusWindowOnClick: true
//                });
                return new Promise(function (resolve, reject) {
                    var messageChannel = new MessageChannel();
                    messageChannel.port1.onmessage = function (event) {
                        if (event.data.error) {
                            reject(event.data.error);
                        } else {
                            resolve(event.data);
                        }
                    };
                    navigator.serviceWorker.controller.postMessage(message, [messageChannel.port2]);
                });
            } else {
                Win.notification(message);
            }
        };

        $rootScope.$on(AUTH_EVENTS.push, function (emit, args) {
            alertService.mobile(args.emit.data);
        });

        return alertService;
    }]);