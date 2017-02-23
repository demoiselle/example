var socket = new WebSocket('wss://push-fwkdemoiselle.rhcloud.com:8443/push/demoiselle');

socket.onopen = function (evt) {
    console.log("Socket opened");
};
socket.onclose = function (evt) {
    console.log("Socket closed");
    setTimeout(function () {
        socket.open();
    }, 3000);
};

socket.onmessage = function (evt) {

    if (evt.data.event === "mensagem" || evt.data.event === "comunicado") {
        var notificationOptions = {
            body: evt.data.data,
            icon: './images/demoiselle.png',
            tag: 'simple-push-demo-notification',
            vibrate: [100, 100, 100, 100, 100]
        };

        self.registration.showNotification('Demoiselle App', notificationOptions);
        console.log(evt);
    }

    if (evt.data.event === "comando") {
        console.log('Comando ativado: ' + evt.data);
    }


};

socket.onerror = function (evt) {
    console.log("Error: " + evt.data);
};

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

