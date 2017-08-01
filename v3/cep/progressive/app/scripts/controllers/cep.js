'use strict';

app.controller('CepController', ['$scope', 'CepService', '$mdSidenav',
    function ($scope, CepService, $mdSidenav) {

        $scope.endereco;
        $scope.uf;
        $scope.cep;
        $scope.logradouro;

        $scope.toggleSidenav = function (menuId) {
            $mdSidenav(menuId).toggle();
        };

        $scope.buscarCep = function () {
            $scope.enderecos = [];
            $scope.endereco = {};
            if ($scope.cep) {
                CepService.getCep($scope.cep).then(
                        function (res) {
                            $scope.endereco = res.data[0];
                        }
                );
            }
        };

        $scope.buscarLogra = function () {
            $scope.endereco = {};
            $scope.enderecos = [];
            if ($scope.uf && $scope.logradouro) {
                CepService.getLogradouro($scope.uf, $scope.logradouro).then(
                        function (res) {
                            $scope.enderecos = res.data;
                        }
                );
            }
        };

        $scope.buscarUf = function () {
            $scope.ufs = [];
            CepService.getUf().then(
                    function (res) {
                        $scope.ufs = res.data;
                    }
            );
        };

        $scope.buscarUf();
    }]);
