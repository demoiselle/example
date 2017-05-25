'use strict';

app.factory('DashboardService', ['$http', function ($http) {
        var service = {};

        service.findCep = function (cep) {
            return $http
                    .get('api/cep?cep=' + cep)
                    .then(function (res) {
                        return res.data;
                    });
        };

        service.findUf = function (uf) {
            return $http
                    .get('api/localidade/uf/' + uf)
                    .then(function (res) {
                        return res.data;
                    });
        };

        service.findLocalidade = function (loc) {
            return $http
                    .get('api/localidade/' + loc)
                    .then(function (res) {
                        return res.data;
                    });
        };

        service.findLogradouro = function (log) {
            return $http
                    .get('api/logradouro/' + log)
                    .then(function (res) {
                        return res.data;
                    });
        };

        return service;
    }]);

