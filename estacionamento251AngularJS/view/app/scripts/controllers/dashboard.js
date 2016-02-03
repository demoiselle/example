'use strict';

app.controller('DashboardController', ['$scope', 'DashboardService', 'VeiculoService',
    function ($scope, DashboardService, VeiculoService) {

        $scope.placa = "";
        $scope.veiculo = "";
        $scope.veiculos = "";

        $scope.buscaPlaca = function () {
            VeiculoService.buscaPlaca($scope.placa).then(function (data) {
                $scope.veiculo = data[0];
                $scope.veiculos.push(data[0]);
            })
        }

    }]);
