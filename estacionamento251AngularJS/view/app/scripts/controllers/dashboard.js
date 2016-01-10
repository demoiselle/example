'use strict';

app.controller('DashboardController', ['$q', '$scope', '$filter', 'DashboardService', 'AlertService', 'AuthService',
    function ($q, $scope, $filter, DashboardService, AlertService, AuthService) {

        $scope.tema = "";
        $scope.temas = [];

//        DashboardService.get().then(function (data) {
//            $scope.temas = data;
//        });

    }]);
