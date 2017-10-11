

appCliente.controller('estadoController', function($scope, $http){
	
	
	$scope.estados=[];
	$scope.estado={};
	
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

	$scope.SalvarEstado = function()
	{
		if($scope.frmEstado.$valid){
			$http({
				 
				  method: 'POST',url: 'http://localhost:8081/estados',data:$scope.estado
				}).then(function (response) {
						

					carregarEstados();
					$scope.estado = {};
					
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
	
	
	$scope.ExcluirEstado = function(estado)
	{
		$http({
			 
			  method: 'DELETE',url: 'http://localhost:8081/estados/'+estado.id
			}).then(function (response) {
			
				    
					$scope.estado.splice($scope.estado.indexOf(estado),1);
				
					console.log(response.data);
					console.log(response.status);
			}, function (response) {
				    console.log(response.data);
					console.log(response.status);
			});
		
		
	}
	
	$scope.AlterarEstado = function(est){
		$scope.estado = angular.copy(est);
		
	}
	
	
	$scope.CancelarAlteracao = function(){
		$scope.estado = {};
		
	}

	
	
	carregarEstados();
	
	
});




