'use strict';

app.controller('UsuarioController', ['$q', '$scope', '$filter', '$location', '$rootScope', '$routeParams', 'UsuarioService', 'AlertService',
    function ($q, $scope, $filter, $location, $rootScope, $routeParams, UsuarioService, AlertService) {

        var orderBy = $filter('orderBy');
        var MIN_NUMBER_OF_NAME_CARACTERS = 3;
        /* Inicializando as variáveis */
        $scope.usuario = {};
        $scope.usuarios = [];


        $scope.findAuxiliar = function () {
        };

        $scope.findAll = function () {
            UsuarioService.findAll().then(
                    function (data) {
                        $scope.usuarios = data;
                        $scope.orderUsuarios = function (predicate, reverse) {
                            $scope.usuarios = orderBy($scope.usuarios, predicate, reverse);
                        };
                        $scope.orderUsuarios('id', false);
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

        $scope.searchByCPForName = function (cpf, nome) {
            UsuarioService.searchByName(nome).then(
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

        $scope.limpar = function () {
            $rootScope.pesquisarFormUsuario = null;
            $rootScope.pesquisaResultadoUsuario = [];
            $scope.usuario = {};
            $scope.usuarios = [];
        };

        $scope.pesquisar = function () {
            if (typeof ($scope.usuario.name) === "undefined" || $scope.usuario.name.length < MIN_NUMBER_OF_NAME_CARACTERS) {
                $scope.usuarios = [];
                AlertService.addWithTimeout('warning', 'Digite pelo menos ' + MIN_NUMBER_OF_NAME_CARACTERS + ' caracteres para realizar a consulta.');
            } else {
                $rootScope.pesquisarFormUsuario = $scope.usuario;
                UsuarioService.pesquisar($scope.usuario.name).then(function (usuarios) {
                    $scope.usuarios = usuarios;
                    $rootScope.pesquisaResultadoUsuario = usuarios;
                    $scope.totalServerItems = usuarios.length;
                    if ($scope.usuarios.length < 1) {
                        AlertService.addWithTimeout('danger', 'Nenhum resultado encontrado.');
                    }
                }, function (res) {
                    AlertService.addWithTimeout('danger', 'Nenhum resultado encontrado.');
                });
            }
        };

        if ($rootScope.pesquisarFormUsuario || $rootScope.pesquisarFormUsuario === $scope.usuario) {
            $scope.usuario = $rootScope.pesquisarFormUsuario;
            $scope.usuarios = $rootScope.pesquisaResultadoUsuario;
        } else {
//		$scope.pesquisar();
        }
        ;

        var id = $routeParams.id;
        var path = $location.$$url;

        if (path === '/usuario') {
            $scope.count();
        }
        ;

        if (path === '/usuario/edit/' + id) {
            UsuarioService.get(id).then(
                    function (data) {
                        $scope.usuario = data;
                        $scope.findAuxiliar();
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
        ;

        $scope.new = function () {
            $location.path('usuario/new');
        };

        $scope.save = function (usuario) {
            $("[id$='-message']").text("");
            UsuarioService.save(usuario).then(
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
            $location.path('/usuario/edit/' + id);
        };

        $scope.toggleSelection = function toggleSelection(grupo) {
            var idx = $scope.isGrupoInGrupos(grupo);
            if (idx > -1) {
                $scope.usuario.grupos.splice(idx, 1);
            } else {
                $scope.usuario.grupos.push(grupo);
            }
        };

        $scope.filterOptions = {
            filterText: '',
            useExternalFilter: false
        };

        $scope.gridOptions = {
            data: 'usuarios',
            columnDefs: [{field: 'id', displayName: '', width: "50"},
                {field: 'name', displayName: 'Nome', width: "30%"},
                {field: 'cpf', displayName: 'CPF', width: "10%"},
                {field: 'telephoneNumber', displayName: 'Telefone', width: "10%"},
                {field: 'email', displayName: 'Email', width: "20%"},
                {field: 'setor', displayName: 'Setor'},
                {displayName: 'Ação', cellTemplate: '<a ng-show="!currentUser" ng-click="edit(row.entity.id)" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-eye-open"></i> Visualizar</a>\n\
                                                 <a ng-show="currentUser" ng-click="edit(row.entity.id)" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-plus-sign"></i> Alterar</a>', width: "80"}],
            selectedItems: [],
            keepLastSelected: true,
            multiSelect: false,
            showFilter: false,
//        enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            filterOptions: $scope.filterOptions,
            enableSorting: true,
            i18n: "pt"
        };

    }]);