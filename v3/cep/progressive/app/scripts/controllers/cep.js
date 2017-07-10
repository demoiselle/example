'use strict';

app.controller('CepController', ['$scope', 'CepService', '$mdSidenav',
    function ($scope, CepService, $mdSidenav) {

        $scope.endereco = {};

        $scope.toggleSidenav = function (menuId) {
            $mdSidenav(menuId).toggle();
        };

        $scope.buscar = function (cep) {
            $scope.endereco = {};
            if (cep) {
                CepService.getCep(cep).then(
                        function (res) {
                            $scope.endereco = res.data[0];
                        }
                );
            }
        };
    }]);
