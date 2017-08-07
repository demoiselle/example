
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
