app.factory('Win', ['$rootScope',
    function ($rootScope) {

        var service = {};

        service.popup = function (mensagem) {
            if (typeof Windows !== 'undefined' &&
                    typeof Windows.UI !== 'undefined' &&
                    typeof Windows.UI.Popups !== 'undefined') {
                // Create the message dialog and set its content
                var msg = new Windows.UI.Popups.MessageDialog(mensagem);
                // Add commands
                msg.commands.append(new Windows.UI.Popups.UICommand("Okay", systemAlertCommandInvokedHandler));
                // Set default command
                msg.defaultCommandIndex = 0;
                // Show the message dialog
                msg.showAsync();
            }
        };

        service.contact = function () {
            if (typeof Windows != 'undefined') {
                // Create the picker 
                var picker = new Windows.ApplicationModel.Contacts.ContactPicker();
                picker.desiredFieldsWithContactFieldType.append(Windows.ApplicationModel.Contacts.ContactFieldType.email);
                // Open the picker for the user to select a contact 
                picker.pickContactAsync().done(function (contact) {
                    if (contact !== null) {
                        var output = "Selecione o contato:\n" + contact.displayName;
                        return output;
                    } else {
                        // The picker was dismissed without selecting a contact 
                        console("Nenhum contato selecionado");
                    }
                });
            }
        };

        service.notification = function (mensagem) {
            if (Windows !== 'undefined' &&
                    Windows.UI !== 'undefined' &&
                    Windows.UI.Notifications !== 'undefined') {
                var notifications = Windows.UI.Notifications;
                //Get the XML template where the notification content will be suplied
                var template = notifications.ToastTemplateType.toastImageAndText01;
                var toastXml = notifications.ToastNotificationManager.getTemplateContent(template);
                //Supply the text to the XML content
                var toastTextElements = toastXml.getElementsByTagName("text");
                toastTextElements[0].appendChild(toastXml.createTextNode(mensagem));
                //Supply an image for the notification
                var toastImageElements = toastXml.getElementsByTagName("image");
                //Set the image this could be the background of the note, get the image from the web
                toastImageElements[0].setAttribute("src", "https://www.apoioescolar.info/images/ms-icon-70x70.png");
                toastImageElements[0].setAttribute("alt", "red graphic");
                //Specify a long duration
                var toastNode = toastXml.selectSingleNode("/toast");
                toastNode.setAttribute("duration", "long");
                //Specify the audio for the toast notification
                var toastNode = toastXml.selectSingleNode("/toast");
                var audio = toastXml.createElement("audio");
                audio.setAttribute("src", "ms-winsoundevent:Notification.IM");
                //Specify launch paramater
                toastXml.selectSingleNode("/toast").setAttribute("launch", '{"type":"toast","param1":"12345","param2":"67890"}');
                //Create a toast notification based on the specified XML
                var toast = new notifications.ToastNotification(toastXml);
                //Send the toast notification
                var toastNotifier = notifications.ToastNotificationManager.createToastNotifier();
                toastNotifier.show(toast);
            }
        };

        return service;

    }]);