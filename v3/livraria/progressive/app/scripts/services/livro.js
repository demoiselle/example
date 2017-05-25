'use strict';
app.factory('LivroService', ['$http', function ($http) {

        var service = {};

        service.findAll = function () {
            return $http
                    .get('api/v1/livros')
                    .then(function (res) {
                        return res.data;
                    });
        };
        service.save = function (livro) {

            return $http({
                url: 'api/v1/livros',
                method: livro.id ? "PUT" : "POST",
                data: livro
            }).then(function (resp) {
                return resp.data;
            });
        };
        service.delete = function (id) {

            return $http
                    .delete('api/v1/livros/' + id)
                    .then(function (res) {
                        return res.data;
                    });
        };
        service.get = function (id) {

            var deferred = $q.defer();
            $http({
                url: 'api/v1/livros/' + id,
                method: "GET"
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject([data, status]);
            });
            return deferred.promise;
        };

        return service;
    }]);
