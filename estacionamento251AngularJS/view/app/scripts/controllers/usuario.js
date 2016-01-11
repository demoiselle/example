'use strict';

app.controller('UsuarioController', ['$scope', '$filter', '$location', '$routeParams', 'UsuarioService', 'AlertService', '$rootScope',
    function ($scope, $filter, $location, $routeParams, UsuarioService, AlertService, $rootScope) {

        var id = $routeParams.id;
        var orderBy = $filter('orderBy');

        $scope.count = function () {
            UsuarioService.count().then(
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

        if (path === '/usuario') {
            $scope.count();
        }
        ;

        if (path === '/usuario/edit') {
            $scope.usuario = {};
        }
        ;

        if (path === '/usuario/edit/' + id) {
            UsuarioService.get(id).then(
                    function (data) {
                        $scope.usuario = data;
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
            $scope.usuarios = [];
            var num = (($scope.currentPage - 1) * $scope.itemsPerPage);
            UsuarioService.list(num, $scope.itemsPerPage).then(
                    function (data) {
                        $scope.usuarios = data;
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

        $scope.new = function () {
            $location.path('/usuario/edit');
        };

        $scope.save = function () {

            $("[id$='-message']").text("");

            UsuarioService.save($scope.usuario).then(
                    function (data) {
                        AlertService.addWithTimeout('success', 'Usuario salvo com sucesso');
                        $location.path('/usuario');
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
            UsuarioService.delete(id).then(
                    function (data) {
                        AlertService.addWithTimeout('success', 'Usuario removido com sucesso');
                        $location.path('/usuario');
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
            $rootScope.usuarioCurrentPage = $scope.pagingOptions.currentPage;
            $location.path('/usuario/edit/' + id);
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
                UsuarioService.list(field, order, init, pageSize).then(
                        function (data) {
                            $scope.usuarios = data;
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

        if ($rootScope.usuarioCurrentPage != undefined) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $rootScope.usuarioCurrentPage);
            $scope.pagingOptions.currentPage = $rootScope.usuarioCurrentPage;
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
            data: 'usuarios',
            columnDefs: [{field: 'id', displayName: '', width: "50"},
                {field: 'name', displayName: 'Usuario'},
                {field: 'email', displayName: 'Email'},
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
            if (next.originalPath.indexOf("usuario") === -1) {
                $rootScope.usuarioCurrentPage = 1;
            }
        });

    }]);