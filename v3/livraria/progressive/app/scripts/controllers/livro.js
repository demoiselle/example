'use strict';

app.controller('LivroController', ['$scope', '$filter', '$location', '$routeParams', 'LivroService', 'AlertService', '$rootScope',
    function ($scope, $filter, $location, $routeParams, LivroService, AlertService, $rootScope) {

    var id = $routeParams.id;
    var path = $location.$$url;

    if (path === '/livro') {
        LivroService.findAll().then(
            function(data) {
                        $scope.livros = data;
            },
            function(error) {
                var data = error[0];
                var status = error[1];

                if (status === 401) {
                    AlertService.addWithTimeout('warning', data.message);
                }
                
            }
        );
    }
    ;

    if (path === '/livro/edit') {
        $scope.livro = {};
    }
    ;

    if (path === '/livro/edit/' + id) {
        LivroService.get(id).then(
            function(data) {
                $scope.livro = data;
            },
            function(error) {
                var data = error[0];
                var status = error[1];

                if (status === 401) {
                    AlertService.addWithTimeout('warning', data.message);
                }
                
            }

        );
    }

    $scope.new = function() {
        $location.path('/livro/edit');
    };

    $scope.save = function() {

        $("[id$='-message']").text("");

        LivroService.save($scope.livro).then(
            function(data) {
                AlertService.addWithTimeout('success', 'Livro salvo com sucesso');
                $location.path('/livro');
            },
            function(error) {

                var data = error[0];
                var status = error[1];

                if (status === 401) {
                    AlertService.addWithTimeout('danger', 'Não foi possível executar a operação');
                }
                else if (status === 412) {
                    $.each(data, function(i, violation) {
                        $("#" + violation.property + "-message").text(violation.message);
                    });
                } 

            }
        );
    };

    $scope.delete = function(id) {
        LivroService.delete(id).then(
            function(data) {
                AlertService.addWithTimeout('success', 'Livro removido com sucesso');
                $location.path('/livro');
            },
            function(error) {
                var data = error[0];
                var status = error[1];

                if (status === 401) {
                    AlertService.addWithTimeout('warning', data.message);
                }
                
            }
        );
    };

    $scope.edit = function(id) {
        $location.path('/livro/edit/' + id);
    };

    $scope.gridOptions = {
        data: 'livros',
        columnDefs: [{field: 'id', name: '', width: "50"},
            {field: 'nome', name: 'Descrição', width: "400"},
            {name: 'Ação', cellTemplate: '<a ng-show="!currentUser" ng-click="edit(row.entity.id)" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-eye-open"></i> Visualizar</a>\n\
                                                 <a ng-show="currentUser" ng-click="edit(row.entity.id)" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-plus-sign"></i> Alterar</a>\n\
                                                 <a has-roles="USER_ROLES.CADASTRADOR, USER_ROLES.ADMINISTRADOR" confirm-button title="Excluir?" confirm-action="delete(row.entity.id)" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-minus-sign"></i> Excluir</a>', width: "200"}]

    };



}]);