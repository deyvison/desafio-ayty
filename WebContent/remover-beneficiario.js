var app = angular.module("myApp", []);

app.controller('controle_remover',function ($scope, $http){

	$scope.removerBeneficiario = function(){

		
		var jsonObj = {cpf: $scope.cpfRemover };
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/removerbeneficiario", jsonObj);
		
		response.success(function(data){
			
			window.alert(data);
			$scope.cpfRemover = "";
		});
		response.error(function(data) {
			window.alert(data);
			$scope.cpfRemover = "";
		});
	}
});
