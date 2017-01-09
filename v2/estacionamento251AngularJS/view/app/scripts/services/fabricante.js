'use strict';

app.factory('FabricanteService', ['$http', '$q', function ($http, $q) {
        var service = {};

        service.findAll = function () {
            return $http.get('api/fabricante').then(function (res) {
                return res.data;
            });
        };

        service.excluir = function (id) {
            return $http.delete('api/fabricante/' + id).then(function (res) {
                return res.data;
            });
        };

        service.save = function (fabricante) {
            var deferred = $q.defer();

            $http({
                url: 'api/fabricante',
                method: fabricante.id ? "PUT" : "POST",
                data: fabricante,
                headers: {
                    'Content-Type': 'application/json;charset=utf8'
                }
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        service.delete = function (id) {
            var deferred = $q.defer();

            $http({
                url: 'api/fabricante/' + id,
                method: "DELETE"
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        service.get = function (id) {

            var deferred = $q.defer();

            $http({
                url: 'api/fabricante/' + id,
                method: "GET"
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        service.list = function (field, order, init, qtde) {
            return $http.get('api/fabricante/list/' + field + '/' + order + '/' + init + '/' + qtde).then(
                function (res) {
                    return res.data;
                }
            );

        };

        service.count = function () {
            var deferred = $q.defer();

            $http({
                url: 'api/fabricante/count',
                method: "GET",
                headers: {
                    'Content-Type': 'application/json;charset=utf8'
                }
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject([data, status]);
            });
            return deferred.promise;
        };

        return service;
    }]);