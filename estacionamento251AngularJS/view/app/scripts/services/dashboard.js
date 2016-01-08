'use strict';

var backendUrl = '';

app.factory('DashboardService', ['$http', function ($http) {
    
    var service = {};

    service.get = function () {
        return $http
                .get('api/tema')
                .then(function (res) {
                    return res.data;
                });
    };

     return service;
}]);

