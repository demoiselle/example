'use strict';

app.factory('UsuarioService', ['$http', '$q', function ($http, $q) {
        var service = {};

        var MIN_NUMBER_OF_NAME_CARACTERS = 5;

        service.findAll = function () {
            var deferred = $q.defer();

            $http({
                url: 'api/user',
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

        service.save = function (usuario) {
            var deferred = $q.defer();

            $http({
                url: 'api/user',
                method: usuario.id ? "PUT" : "POST",
                data: usuario,
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
                url: 'api/user/' + id,
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
                url: 'api/user/' + id,
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
                url: 'api/user/list/' + field + '/' + order + '/' + init + '/' + qtde,
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
                url: 'api/user/count',
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

