'use strict';

app.factory('DashboardService', ['$http', function ($http) {

        var service = {};

        service.get = function () {
            return $http
                    .get('api/dash')
                    .then(function (res) {
                        return res.data;
                    });
        };

        return service;
    }]);

