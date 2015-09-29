var app = angular.module('myapp', []);

app.controller('controle_consulta',function ($scope, $http){

	
	
	$scope.retorno = [];
	
	$scope.pesquisarBeneficiario = function(){
		
		
		
		var jsonObj = {pesquisa: $scope.pesquisa};
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/consultarbeneficiario", jsonObj);
		response.success(function(data){
			
			
			
			if(data.cpf !== undefined){
				
				//$scope.result1 = data.nome;
				//$scope.result2 = data.estado_civil;
				//$scope.result3 = data.data_nascimento;
			//	$scope.result4 = data.nacionalidade;
				//$scope.result5 = data.estado_nascimento;
			//	$scope.result6 = data.cidade_nascimento;
			//	$scope.result7 = data.sexo;
			//	$scope.result8 = data.cpf;
			//	$scope.result9 = data.rg;
				
			//	$scope.retorno = [data];
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
	
	$scope.removeLinha = function (linha){
		var index = $scope.retorno.indexOf(linha);
		if (index !== -1) {
            $scope.retorno.splice(index, 1);
        }
	}
});
