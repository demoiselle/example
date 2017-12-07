// React when a browser action's icon is clicked.
chrome.browserAction.onClicked.addListener(function (tab) {
    var viewTabUrl = chrome.extension.getURL('https://forum.demoiselle.org/index.html');
    var imageUrl = 'https://forum.demoiselle.org/assets/logo.png';

    // Look through all the pages in this extension to find one we can use.
    var views = chrome.extension.getViews();
    for (var i = 0; i < views.length; i++) {
        var view = views[i];

        // If this view has the right URL and hasn't been used yet...
        if (view.location.href == viewTabUrl && !view.imageAlreadySet) {

            // ...call one of its functions and set a property.
            view.setImageUrl(imageUrl);
            view.imageAlreadySet = true;
            break; // we're done
        }
    }
});
