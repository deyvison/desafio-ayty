var app = angular.module('myapp', []);

app.controller('controle_consulta',function ($scope, $http){

	
	
	$scope.retorno = [];
	
	$scope.pesquisarBeneficiario = function(){
		
		
		if($scope.pesquisa === undefined || $scope.pesquisa === ""){
			
			window.alert("Campo em branco!");
		}else{
			var jsonObj = {pesquisa: $scope.pesquisa};
			var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/consultarbeneficiario", jsonObj);
			response.success(function(data){
				
				
				if(data.cpf !== undefined){
					$scope.retorno.push(data);
				}else{
					window.alert("Beneficiario não cadastrado!");
					
				}
				
				$scope.pesquisa = "";
			});
			response.error(function(data) {
				window.alert(data);
				$scope.pesquisa = "";
			});
		}
		
		
	}
	
	$scope.removeLinha = function (linha){
		var index = $scope.retorno.indexOf(linha);
		if (index !== -1) {
            $scope.retorno.splice(index, 1);
        }
	}
	
	$scope.listarTodos = function(){
		
		var response = $http.get("http://localhost:8080/desafio-ayty/rest/app/listarTodosBeneficiarios");
		
		response.success(function(data){
			
			$scope.retorno = data;
			
		});
		response.error(function(data) {
			window.alert(data);
			
		});
	}
	
	$scope.excluirBeneficiarioPorLinha = function (linha){
		
		var index = $scope.retorno.indexOf(linha);
		var cpfRemover = $scope.retorno[index].cpf;
		
		var jsonObj = {cpf: cpfRemover};
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/removerbeneficiario", jsonObj);
		
		response.success(function(data){
			
			window.alert(data);
			$scope.cpfRemover = "";
			$scope.retorno.splice(index, 1);
		});
		response.error(function(data) {
			window.alert(data);
			$scope.cpfRemover = "";
			
		});
		
		
	}
});
