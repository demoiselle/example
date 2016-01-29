angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    
      
        
    .state('tabsController.estacionamento', {
      url: '/estacionamento',
      views: {
        'tab1': {
          templateUrl: 'templates/estacionamento.html',
          controller: 'estacionamentoCtrl'
        }
      }
    })
        
      
    
      
        
    .state('tabsController.fabricante', {
      url: '/fabricante',
      views: {
        'tab2': {
          templateUrl: 'templates/fabricante.html',
          controller: 'fabricanteCtrl'
        }
      }
    })
        
      
    
      
        
    .state('meuPrefil', {
      url: '/eu',
      templateUrl: 'templates/meuPrefil.html',
      controller: 'meuPrefilCtrl'
    })
        
      
    
      
    .state('tabsController', {
      url: '/page3',
      abstract:true,
      templateUrl: 'templates/tabsController.html'
    })
      
    
      
        
    .state('login', {
      url: '/page7',
      templateUrl: 'templates/login.html',
      controller: 'loginCtrl'
    })
        
      
    ;

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/page3/estacionamento');

});