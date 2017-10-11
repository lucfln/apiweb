var appCliente = angular.module('appCliente',['ngRoute']);



appCliente.config(function( $routeProvider, $locationProvider) {
	 
	  $routeProvider
	    .when("/clientes", {
	      templateUrl: "view/cliente.html", 
	      controller: "clienteController"
	    })
	    .when("/clientes/:clienteId", {
		      templateUrl: "view/cliente-detalhe.html", 
		      controller: "clienteDetalheController"		    	  
		    	            
		 })
		 .when("/estados", {
	      templateUrl: "view/estado.html", 
	      controller: "estadoController"
		 })
		 .when("/login", {
	      templateUrl: "view/login.html", 
	      controller: "loginController"
		 })		 
	    .otherwise({ redirectTo: "/" });
	    
	  
	  $locationProvider.html5Mode({ enabled: true, requireBase: false });
	     

});


appCliente.config(function($httpProvider){
	$httpProvider.interceptors.push("tokenInterceptor");
	
});



