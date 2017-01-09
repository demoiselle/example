angular.module('app.controllers', [])

        .controller('estacionamentoCtrl', ['$scope', 'Service', function ($scope, Service) {

                $scope.placa = "";
                $scope.veiculo;

                $scope.buscaPlaca = function (placa) {
                    Service.buscaPlaca(placa).then(function (data) {
                        $scope.veiculo = data[0];
                    })
                }

            }])

        .controller('fabricanteCtrl', function ($scope) {

        })

        .controller('meuPrefilCtrl', function ($scope) {

        })

        .controller('loginCtrl', function ($scope) {

        })
 