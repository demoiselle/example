'use strict';

app.factory('ConstanteService', ['$http', function ($http) {
        var service = {};

        service.perfils = function () {
            return $http.get('api/constantes/perfil').then(function (res) {
                return res;
            });
        };

        service.horas = function () {
            return ['08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30', '20:00', '20:30', '21:00', '21:30', '22:00', '22:30'];
        };

        service.diasdasemana = function () {
            return [{"id": 1, "description": "Domingo"}, {"id": 2, "description": "Segunda"}, {"id": 3, "description": "Terça"}, {"id": 4, "description": "Quarta"}, {"id": 5, "description": "Quinta"}, {"id": 6, "description": "Sexta"}, {"id": 7, "description": "Sábado"}];
        };


        return service;
    }]);
