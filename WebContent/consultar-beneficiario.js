var app = angular.module('myapp', []);

app.controller('controle_consulta',function ($scope, $http){

	$scope.pesquisarBeneficiario = function(){

		
		var jsonObj = {pesquisa: $scope.pesquisa};
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/consultarbeneficiario", jsonObj);
		console.log("sim vamos");
		response.success(function(data){
			
			window.alert(data)
			$scope.pesquisa = "";
		});
		response.error(function(data) {
			window.alert(data);
			$scope.pesquisa = "";
		});
	}
});
