angular.module('app.services', [])

        .service('Service', ['$http', function ($http) {
                var service = {};
                service.buscaPlaca = function (placa) {
                    return $http.get('https://estacionamento-fwkdemoiselle.rhcloud.com/api/veiculo/placa/' + placa).then(function (res) {
                        return res.data;
                    });
                };
                return service;
            }]);

