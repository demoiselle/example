'use strict';

app.factory('LivroService', ['$http', function ($http) {
        var service = {};

        service.get = function (id) {
            return $http.get('api/v1/livros/' + id).then(function (res) {
                return res;
            });
        };

        service.delete = function (id) {
            return $http.delete('api/v1/livros/' + id).then(function (res) {
                return res;
            });
        };

        service.save = function (livro) {
            return $http({
                url: 'api/v1/livros',
                method: livro.id ? "PUT" : "POST",
                data: livro
            }).then(
                    function (res) {
                        return res;
                    }
            );
        };

        service.saveLote = function (livros) {
            return $http({
                url: 'api/v1/livros/lote/',
                method: "POST",
                data: livros
            }).then(
                    function (res) {
                        return res;
                    }
            );
        };

        service.list = function (field, order, init, qtde) {
            return $http.get('api/v1/livros/' + '?sort=' + field).then(
                    function (res) {
                        return res;
                    }
            );

        };

        service.findAll = function () {
            return $http.get('api/v1/livros?sort=descricao').then(function (res) {
                return res;
            });
        };

        service.searchByAula = function (id) {
            return $http.get('api/v1/livros/aula/' + id).then(function (res) {
                return res;
            });
        };

        service.searchByDia = function (id) {
            return $http.get('api/v1/livros/data/' + id).then(function (res) {
                return res;
            });
        };


        return service;
    }]);


