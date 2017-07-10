angular.module('app').run(['$templateCache', function($templateCache) {
  'use strict';

  $templateCache.put('views/cep.html',
    "<md-card flex-gt-sm=55 flex-gt-md=55><md-tabs md-dynamic-height md-border-bottom><md-tab label=CEP><md-content class=md-padding><md-card-content><md-input-container class=md-block><label>CEP</label><input ng-model=cep><md-button ng-click=buscar(cep)>Buscar</md-button></md-input-container></md-card-content><md-card-actions layout=row layout-align=\"left center\" ng-show=endereco.id><br>Cidade: {{endereco.cidade}} - {{endereco.uf}}<br>Bairro: {{endereco.bairroIni}} {{endereco.bairroFim}}<br>Logradouro: {{endereco.logradouro}}</md-card-actions></md-content></md-tab></md-tabs></md-card>"
  );


  $templateCache.put('views/partials/alerts.html',
    "<aside class=\"toast-view js-toast-view\"><div ng-repeat=\"alert in alerts\" class=\"alert alert-{{alert.type}} alert-dismissable animated fadeInDown\"><button type=button class=close data-dismiss=alert aria-hidden=true>&times;</button> {{alert.msg}}</div></aside>"
  );


  $templateCache.put('views/partials/loading.html',
    "<img src=images/725.gif alt=\"\"/>"
  );

}]);
