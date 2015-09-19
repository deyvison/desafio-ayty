var app = angular.module("Myapp", []);

app.controller('autenticacao',function ($scope, $http){

	$scope.enviarDados = function(){

		
		var jsonObj = {login: $scope.login, senha : $scope.senha};
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/consultarlogin", jsonObj);
		console.log(jsonObj.login);
		console.log(jsonObj.senha);
		response.success(function(data){
			
			if(data === "Usuário autenticado!"){
				alert("Entrouuuuuuuuu");
				window.open("beneficiario.html","_self");
			}else{
				alert("Usuário inválido!");
			}
		});
		response.error(function(data) {
  		alert(data);
		});
	}
});
