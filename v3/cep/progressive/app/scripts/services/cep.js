'use strict';

app.factory('CepService', ['$http', function ($http) {
        var service = {};

        service.getMunicipios = function (uf) {
            return $http.get('api/v1/cidades/' + uf).then(function (res) {
                return res;
            });
        };

        service.getUf = function () {
            return $http.get('api/v1/ufs').then(function (res) {
                return res;
            });
        };

        service.getCep = function (cep) {
            return $http.get('https://cep-fwkdemoiselle.rhcloud.com/api/v1/ceps?cep=' + cep).then(function (res) {
                return res;
            });
        };

        return service;
    }]);
