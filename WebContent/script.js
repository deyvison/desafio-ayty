var app = angular.module("Myapp", []);

app.controller('autenticacao',function ($scope, $http){

	$scope.enviarDados = function(){

		console.log("Entrou aqui");
		var jsonObj = {login: $scope.login, senha : $scope.senha};
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/consultarlogin", jsonObj);
		console.log(jsonObj.login);
		console.log(jsonObj.senha);
		response.success(function(data){
			alert("Succes "+data)
		});
		response.error(function(data) {
  		alert("Error "+data);
		});
	}
});
