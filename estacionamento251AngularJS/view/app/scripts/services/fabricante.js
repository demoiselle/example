'use strict';

app.factory('FabricanteService', ['$http', '$q', function($http, $q) {
        var service = {};

        service.findAll = function() {
            var deferred = $q.defer();

            $http({
                url: 'api/fabricante',
                method: "GET",
                headers: {
                    'Content-Type': 'application/json;charset=utf8'
                }
            }).success(function(data) {
                deferred.resolve(data);
            }).error(function(data, status) {
                deferred.reject([data, status]);
            });
            return deferred.promise;
        };


        service.save = function(fabricante) {
            var deferred = $q.defer();

            $http({
                url: 'api/fabricante',
                method: fabricante.id ? "PUT" : "POST",
                data: fabricante,
                headers: {
                    'Content-Type': 'application/json;charset=utf8'
                }
            }).success(function(data) {
                deferred.resolve(data);
            }).error(function(data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        service.delete = function(id) {
            var deferred = $q.defer();

            $http({
                url: 'api/fabricante/' + id,
                method: "DELETE"
            }).success(function(data) {
                deferred.resolve(data);
            }).error(function(data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        service.get = function(id) {

            var deferred = $q.defer();

            $http({
                url: 'api/fabricante/' + id,
                method: "GET"
            }).success(function(data) {
                deferred.resolve(data);
            }).error(function(data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

    service.list = function(field, order, init, qtde) {
        var deferred = $q.defer();

        $http({
            url: 'api/fabricante/list/' + field + '/' + order + '/' + init + '/' + qtde,
            method: "GET",
            headers: {
                'Content-Type': 'application/json;charset=utf8'
            }
        }).success(function(data) {
            deferred.resolve(data);
        }).error(function(data, status) {
            deferred.reject([data, status]);
        });
        return deferred.promise;
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