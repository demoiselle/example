'use strict';

app.controller('FabricanteController', ['$scope', '$filter', '$location', '$routeParams', 'FabricanteService', 'AlertService', '$rootScope',
    function ($scope, $filter, $location, $routeParams, FabricanteService, AlertService, $rootScope) {

        var id = $routeParams.id;
        var orderBy = $filter('orderBy');

        $scope.count = function () {
            FabricanteService.count().then(
                function (data) {
                    $scope.totalServerItems = data;
                },
                function (error) {
                    var data = error[0];
                    var status = error[1];

                    if (status === 401) {
                        AlertService.addWithTimeout('warning', data.message);
                    }

                }
            );
        };

        var id = $routeParams.id;
        var path = $location.$$url;

        if (path === '/fabricante') {
            $scope.count();
        }
        ;

        if (path === '/fabricante/edit') {
            $scope.fabricante = {};
        }
        ;

        if (path === '/fabricante/edit/' + id) {
            FabricanteService.get(id).then(
                function (data) {
                    $scope.fabricante = data;
                },
                function (error) {
                    var data = error[0];
                    var status = error[1];

                    if (status === 401) {
                        AlertService.addWithTimeout('warning', data.message);
                    }

                }

            );
        }

        $scope.pageChanged = function () {
            $scope.fabricantes = [];
            var num = (($scope.currentPage - 1) * $scope.itemsPerPage);
            FabricanteService.list(num, $scope.itemsPerPage).then(
                function (data) {
                    $scope.fabricantes = data;
                },
                function (error) {
                    if (error.status === 401) {
                        AlertService.addWithTimeout('warning', error.data.message);
                    }

                }
            );
        };

        $scope.new = function () {
            $location.path('/fabricante/edit');
        };

        $scope.save = function () {

            $("[id$='-message']").text("");

            FabricanteService.save($scope.fabricante).then(
                function (data) {
                    AlertService.addWithTimeout('success', 'Fabricante salvo com sucesso');
                    $location.path('/fabricante');
                },
                function (error) {

                    var data = error[0];
                    var status = error[1];

                    if (status === 401) {
                        AlertService.addWithTimeout('danger', 'Não foi possível executar a operação');
                    } else if (status === 412) {
                        $.each(data, function (i, violation) {
                            $("#" + violation.property + "-message").text(violation.message);
                        });
                    }

                }
            );
        };

        $scope.delete = function (id) {
            FabricanteService.delete(id).then(
                function (data) {
                    AlertService.addWithTimeout('success', 'Fabricante removido com sucesso');
                    $location.path('/fabricante');
                    $scope.count();
                    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
                },
                function (error) {
                    var data = error[0];
                    var status = error[1];

                    if (status === 401) {
                        AlertService.addWithTimeout('warning', data.message);
                    }

                }
            );
        };

        $scope.edit = function (id) {
            $rootScope.fabricanteCurrentPage = $scope.pagingOptions.currentPage;
            $location.path('/fabricante/edit/' + id);
        };

        function buscaElemento(elemento, lista) {
            var index = -1;
            for (var i = 0; i < lista.length; i++) {
                if (lista[i].nome === elemento.nome) {
                    index = i;
                    break;
                }
            }
            return index;
        }

        $scope.filterOptions = {
            filterText: '',
            externalFilter: 'searchText',
            useExternalFilter: true
        };

        $scope.pagingOptions = {
            pageSizes: [15],
            pageSize: 15,
            currentPage: 1
        };

        $scope.setPagingData = function (data, page, pageSize) {
            var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
            $scope.myData = pagedData;
            $scope.totalServerItems = data.length;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };

        $scope.getPagedDataAsync = function (pageSize, page) {
            var field;
            var order;
            if (typeof ($scope.sortInfo) === "undefined") {
                field = "id";
                order = "asc";
            } else {
                field = $scope.sortInfo.fields[0];
                order = $scope.sortInfo.directions[0];
            }

            setTimeout(function () {
                var init = (page - 1) * pageSize;
                FabricanteService.list(field, order, init, pageSize).then(
                    function (data) {
                        $scope.fabricantes = data;
                    },
                    function (error) {
                        var data = error[0];
                        var status = error[1];

                        if (status === 401) {
                            AlertService.addWithTimeout('warning', data.message);
                        }
                    }
                );
            }, 100);
        };

        if ($rootScope.fabricanteCurrentPage != undefined) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $rootScope.fabricanteCurrentPage);
            $scope.pagingOptions.currentPage = $rootScope.fabricanteCurrentPage;
        } else {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }


        $scope.$watch('pagingOptions', function (newVal, oldVal) {
            if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, true);

        $scope.$watch('filterOptions', function (newVal, oldVal) {
            if (newVal !== oldVal) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, true);

        $scope.$watch('sortInfo', function (newVal, oldVal) {
            if (newVal !== oldVal) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, true);

        $scope.$on('ngGridEventSorted', function (event, sortInfo) {
            $scope.sortInfo = sortInfo;
        });

        $scope.gridOptions = {
            data: 'fabricantes',
            columnDefs: [{field: 'id', displayName: '', width: "50"},
                {field: 'year', displayName: 'Ano', width: "50"},
                {field: 'make', displayName: 'Fabricante'},
                {field: 'model', displayName: 'Modelo'},
                {displayName: 'Ação', cellTemplate: '<a ng-show="!currentUser" ng-click="edit(row.entity.id)" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-eye-open"></i> Visualizar</a>\n\
                                                 <a ng-show="currentUser" ng-click="edit(row.entity.id)" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-plus-sign"></i> Alterar</a>\n\
                                                 <a has-roles="ADMINISTRADOR" confirm-button title="Excluir?" confirm-action="delete(row.entity.id)" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-minus-sign"></i> Excluir</a>', width: "200"}],
            selectedItems: [],
            keepLastSelected: true,
            sortInfo: $scope.sortInfo,
            multiSelect: false,
            enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            enableSorting: true,
            useExternalSorting: true,
            i18n: "pt"
        };

        $scope.$on('$routeChangeStart', function (event, next) {
            if (next.originalPath.indexOf("fabricante") === -1) {
                $rootScope.fabricanteCurrentPage = 1;
            }
        });

    }]);