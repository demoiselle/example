'use strict';

app.controller('LivroController', ['$scope', '$location', '$routeParams', '$rootScope', 'LivroService', 'AlertService', 'ValidationService',
    function ($scope, $location, $routeParams, $rootScope, LivroService, AlertService, ValidationService) {

        $scope.livro = {};

        var id = $routeParams.id;
        var path = $location.$$url;

        if (path === '/livro') {
            ValidationService.clear();
            $scope.livros = [];
            LivroService.findAll().then(
                    function (res) {
                        $scope.livros = res.data;
                    },
                    function (res) {

                        var data = res.data;
                        var status = res.status;
                        var message = res.message;

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', message);
                        } else if (status === 412 || status === 422) {
                            ValidationService.registrarViolacoes(data);
                        } else if (status === 403) {
                            AlertService.showMessageForbiden();
                        }

                    }
            );
        }

        if (path === '/livro/edit') {
            ValidationService.clear();
            $scope.livro = {};
        }


        if (path === '/livro/edit/' + id) {
            ValidationService.clear();
            LivroService.get(id).then(
                    function (res) {
                        $scope.livro = res.data;
                        $scope.livro.dia = new Date($scope.livro.dia + 'T03:00:00Z');
                    },
                    function (res) {

                        var data = res.data;
                        var status = res.status;
                        var message = res.message;

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', message);
                        } else if (status === 412 || status === 422) {
                            ValidationService.registrarViolacoes(data);
                        } else if (status === 403) {
                            AlertService.showMessageForbiden();
                        }

                    }

            );
        }

        $scope.new = function () {
            $location.path('/livro/edit');
        };

        $scope.save = function () {

            LivroService.save($scope.livro).then(
                    function (res) {
                        AlertService.addWithTimeout('success', 'Livro salvo com sucesso');
                        $location.path('/livro');
                    },
                    function (res) {

                        var data = res.data;
                        var status = res.status;
                        var message = res.message;

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', message);
                        } else if (status === 412 || status === 422) {
                            ValidationService.registrarViolacoes(data);
                        } else if (status === 403) {
                            AlertService.showMessageForbiden();
                        }

                    }
            );
        };

        $scope.delete = function (id) {
            LivroService.delete(id).then(
                    function () {
                        AlertService.addWithTimeout('success', 'Livro removido com sucesso');
                        $location.path('/livro');
                        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
                    },
                    function (res) {

                        var data = res.data;
                        var status = res.status;
                        var message = res.message;

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', message);
                        } else if (status === 412 || status === 422) {
                            ValidationService.registrarViolacoes(data);
                        } else if (status === 403) {
                            AlertService.showMessageForbiden();
                        }

                    }
            );
        };

        $scope.edit = function (id) {
            $location.path('/livro/edit/' + id);
        };

        $scope.gridOptions = {
            paginationPageSizes: [13],
            paginationPageSize: 13,
            data: 'livros',
            i18n: "pt",
            columnDefs: [
                {field: 'description', name: 'Descricão', width: "450"},
                {field: 'dia', name: 'Dia', width: "150", cellFilter: 'date:\'dd-MM-yyyy\''},
                {name: 'Ação', cellTemplate: '<a has-roles="COORDENADOR" ng-click="grid.appScope.edit(row.entity.id)" class="btn btn-warning btn-xs"><i class="glyphicon glyphicon-plus-sign"></i> Alterar</a>\n\
                                                 <a has-roles="COORDENADOR" confirm-button title="Excluir?" confirm-action="grid.appScope.delete(row.entity.id)" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-minus-sign"></i> Excluir</a>', width: "200"}]

        };

    }]);