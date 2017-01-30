'use strict';

app.factory('AlertService', ['$rootScope', '$timeout',
    function ($rootScope, $timeout) {
        var alertService = {};

        // create an array of alerts available globally
        $rootScope.alerts = [];

        alertService.addWithTimeout = function (type, msg, timeout) {
            var alert = alertService.add(type, msg);
            $timeout(function () {
                alertService.closeAlert(alert);
            }, timeout ? timeout : 4000);
        };

        alertService.add = function (type, msg, timeout) {
            if (type && msg) {
                $rootScope.alerts.push({
                    'type': type,
                    'msg': msg
                });
            }
        };

        alertService.showMessageForbiden = function () {
            this.addWithTimeout('danger', 'Você não tem permissão para executar essa operação');
        };

        alertService.closeAlert = function (alert) {
            return this.closeAlertIdx($rootScope.alerts.indexOf(alert));
        };

        alertService.closeAlertIdx = function (index) {
            return $rootScope.alerts.splice(index, 1);
        };

        alertService.clear = function () {
            $rootScope.alerts = [];
        };

        alertService.notification = function (message) {
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
        };


        return alertService;
    }]);