'use strict';

app.controller('DashboardController', ['$rootScope', '$scope', 'DashboardService', 'VeiculoService', 'WS',
    function ($rootScope, $scope, DashboardService, VeiculoService, WS) {

        $scope.placa = "";
        $scope.veiculo = "";
        $scope.veiculos = [];

        $scope.buscaPlaca = function () {
            VeiculoService.buscaPlaca($scope.placa).then(function (data) {
                $scope.veiculo = data[0];
                WS.command("placa", $scope.placa);
            })
        }

        $rootScope.$on("websocket", function (emit, args) {

            if (args.emit.event === "placa") {
                $scope.veiculos.push(args.emit.data);
            }

        });

    }]);
