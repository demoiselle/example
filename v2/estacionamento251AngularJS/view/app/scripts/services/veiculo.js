'use strict';

app.factory('VeiculoService', ['$http', '$q', function ($http, $q) {
        var service = {};

        service.findAll = function () {
            var deferred = $q.defer();

            $http({
                url: 'api/veiculo',
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

        service.buscaPlaca = function (placa) {
            var deferred = $q.defer();

            $http({
                url: 'api/veiculo/placa/' + placa,
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

        service.save = function (veiculo) {
            var deferred = $q.defer();

            $http({
                url: 'api/veiculo',
                method: veiculo.id ? "PUT" : "POST",
                data: veiculo,
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
                url: 'api/veiculo/' + id,
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
                url: 'api/veiculo/' + id,
                method: "GET"
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        service.list = function (field, order, init, qtde) {
            var deferred = $q.defer();

            $http({
                url: 'api/veiculo/list/' + field + '/' + order + '/' + init + '/' + qtde,
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

        service.count = function () {
            var deferred = $q.defer();

            $http({
                url: 'api/veiculo/count',
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
