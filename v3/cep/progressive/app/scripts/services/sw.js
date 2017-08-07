'use strict';

app.factory('SWService', ['$http', function ($http) {
        var service = {};

        if ('serviceWorker' in navigator) {
            window.addEventListener('load', function () {
                navigator.serviceWorker.register('/cep/service-worker.js').then(function (registration) {
                    // Registration was successful
                    console.log('ServiceWorker registration successful with scope: ', registration.scope);

                    registration.pushManager.subscribe({userVisibleOnly: true}).then(
                            function (pushSubscription) {
                               // console.log(pushSubscription);
                            }, function (error) {
                        console.log(error);
                    }
                    );

                }).catch(function (err) {
                    // registration failed :(
                    console.log('ServiceWorker registration failed: ', err);
                });
            });
        }

        return service;
    }]);
