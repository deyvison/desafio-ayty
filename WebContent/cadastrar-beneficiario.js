var app = angular.module('myApp', []);

app.controller('controle_cadastro',function ($scope, $http){

	$scope.cadastrarBeneficiario = function(){

		var jsonObj = {nome: $scope.nome, estado_civil: $scope.estado_civil, data_de_nasc: $scope.data_de_nasc, 
				nacionalidade: $scope.nacionalidade, estado_nasc: $scope.estado_nasc, cidade_nasc: $scope.cidade_nasc, sexo: $scope.sexo,
				cpf: $scope.cpf, rg: $scope.rg};
		
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/cadastrarbeneficiario", jsonObj);
		
		response.success(function(data){
			
			window.alert(data)
			$scope.nome = "";
			$scope.estado_civil = "";
			$scope.data_de_nasc = "";
			$scope.nacionalidade = "";
			$scope.estado_nasc = "";
			$scope.cidade_nasc = "";
			$scope.sexo = "";
			$scope.cpf = "";
			$scope.rg = "";
		});
		
		response.error(function(data) {
			window.alert(data);
		});
	}
});
