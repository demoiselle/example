'use strict';

app.controller('DashboardController', ['$scope', 'DashboardService', 'AlertService',
    function ($scope, DashboardService, AlertService) {

        $scope.limpar = function () {
            $scope.logradouro = {};
            $scope.logradouros = [];
            $scope.localidade = {};
            $scope.localidades = [];
            $scope.searchText = "";
            $scope.cep = "";
            $scope.uf = "";
            $scope.loc = "";
            $scope.log = "";
        };

        $scope.limpar();

        $scope.findCep = function () {
            DashboardService.findCep($scope.cep).then(
                    function (data) {
                        $scope.logradouro = data;
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

        $scope.findUf = function () {
            DashboardService.findUf($scope.uf).then(
                    function (data) {
                        $scope.localidades = data;
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

        $scope.findLocalidade = function () {
            DashboardService.findLocalidade($scope.loc).then(
                    function (data) {
                        $scope.localidades = data;
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

        $scope.findLogradouro = function () {
            DashboardService.findLogradouro($scope.log).then(
                    function (data) {
                        $scope.logradouros = data;
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

    }]);
