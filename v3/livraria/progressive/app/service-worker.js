var ws;
var connect = function () {
    ws = new WebSocket('wss://push-fwkdemoiselle.rhcloud.com:8443/push/cep');

    ws.onopen = function () {
        console.log('socket open');
    };

    ws.onerror = function () {
        console.log('socket error');
    };

    ws.onclose = function () {
        console.log('socket close');
        setTimeout(connect(), 10000);
    };

    ws.onmessage = function (evt) {
        var evento = JSON.parse(evt.data);
        if (evento.event === "mensagem" || evento.event === "comunicado") {
            var notificationOptions = {
                body: evento.data,
                icon: './images/demoiselle.png',
                tag: 'simple-push-demo-notification',
                vibrate: [100, 100, 100, 100, 100]
            };

            self.registration.showNotification('Demoiselle App CEP', notificationOptions);
            console.log(evt);
        }

        if (evento.event === "comando") {
            console.log('Comando ativado: ' + evento);
        }
        console.log('Message: ' + evento.event + ' ' + evento.data);
    };
};

connect();

// Install Service Worker
self.addEventListener('install', function (event) {
    console.log('installed!');
});

// Service Worker Active
self.addEventListener('activate', function (event) {
    console.log('activated!');
});

self.addEventListener('message', function (event) {

    var notificationOptions = {
        body: event.data,
        icon: './images/demoiselle.png',
        tag: 'simple-push-demo-notification',
        vibrate: [100, 100, 100, 100, 100]
    };

    return self.registration.showNotification('CEPs Atualizados!', notificationOptions);

});

self.addEventListener('push', function (event) {

    var notificationOptions = {
        body: event.data,
        icon: './images/demoiselle.png',
        tag: 'simple-push-demo-notification',
        vibrate: [100, 100, 100, 100, 100]
    };

    return self.registration.showNotification('CEPs Atualizados!', notificationOptions);

});

self.addEventListener('notificationclick', function (event) {

    event.waitUntil(
            clients.matchAll({
                type: "window"
            })
            .then(function (clientList) {
                for (var i = 0; i < clientList.length; i++) {
                    var client = clientList[i];
                    if (client.url == '/' && 'focus' in client)
                        return client.focus();
                }
                if (clients.openWindow) {
                    return clients.openWindow('/');
                }
            })
            );

});
