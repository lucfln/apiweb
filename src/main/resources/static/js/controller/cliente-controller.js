

appCliente.controller('clienteController', function($scope, $http){
	
	
	$scope.clientes=[];
	$scope.cliente={};
	$scope.cliente.estado={};
	
	carregarClientes = function(){
		
		token =localStorage.getItem("userToken");
		
		//$http.defaults.headers.common.Authorization = 'Bearer ' + token;
		
		$http({
			 
			  method: 'GET',url: '/admin/clientes'
			}).then(function (response) {
					$scope.clientes=response.data;				
			}, function (response) {
				    console.log(response.data);
					console.log(response.status);
			});
	
		} 

	
	carregarEstados = function(){
		$http({			 
			  method: 'GET',url: 'http://localhost:8081/estados'
			}).then(function (response) {
					$scope.estados=response.data;				
			}, function (response) {
				    console.log(response.data);
					console.log(response.status);
			});

		} 

	
	
	
	$scope.SalvarCliente = function()
	{
		if($scope.frmCliente.$valid){
			
			$http({				 
				  method: 'POST',url: '/admin/clientes', data:$scope.cliente
				}).then(function (response) {
						
					//$scope.clientes.push(response.data);
					carregarClientes();
					$scope.cliente = {};
					
					console.log(response.data);
					console.log(response.status);
				}, function (response) {
					    console.log(response.data);
						console.log(response.status);
				});
		}else{
			window.alert("Dados Inválidos!");
			
		}
		
		
	}
	
	
	$scope.ExcluirCliente = function(cliente)
	{
		$http({
			 
			  method: 'DELETE',url: '/admin/clientes/'+cliente.id
			}).then(function (response) {
				
				/*for (i=0; i<$scope.clientes.length; i++)
				{
					if($scope.clientes[i].id==cliente.id)
						{	
							$scope.clientes.splice(i,1);
							break;
						}
					
				}*/
				    
					$scope.clientes.splice($scope.clientes.indexOf(cliente),1);
				
					console.log(response.data);
					console.log(response.status);
			}, function (response) {
				    console.log(response.data);
					console.log(response.status);
			});
		
		
	}
	
	$scope.AlterarCliente = function(cli){
		$scope.cliente = angular.copy(cli);
		
	}
	
	
	$scope.CancelarAlteracao = function(){
		$scope.cliente = {};
		
	}

	
	
	carregarClientes();
	carregarEstados();
	
	
});




