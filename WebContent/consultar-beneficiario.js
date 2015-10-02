var app = angular.module('myapp', []);

app.controller('controle_consulta',function ($scope, $http){

	$scope.mostrar = false;
	
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
		
		if(window.confirm("Deseja deletar permanentemente do sistema?")){
			
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
	}
	
	$scope.mostrarCampoEditar = function(linha){
		$scope.mostrar = true;
		
		$scope.nomeEditar = linha.nome;
		$scope.estadoCivilEditar = linha.estado_civil;
		$scope.dataNascimentoEditar = linha.data_nascimento;
		$scope.nacionalidadeEditar = linha.nacionalidade;
		$scope.estadoNascimentoEditar = linha.estado_nascimento;
		$scope.cidadeNascimentoEditar = linha.cidade_nascimento;
		$scope.sexoEditar = linha.sexo;
		$scope.cpfEditar = linha.cpf;
		$scope.rgEditar = linha.rg;
		
	}
	
	$scope.alterarUsuario = function(){
		
		
		var jsonObj = {nome: $scope.nomeEditar, estado_civil: $scope.estadoCivilEditar, data_nascimento: $scope.dataNascimentoEditar, 
				nacionalidade: $scope.nacionalidadeEditar, estado_nascimento: $scope.estadoNascimentoEditar, 
				cidade_nascimento: $scope.cidadeNascimentoEditar, sexo: $scope.sexoEditar, cpf: $scope.cpfEditar, rg: $scope.rgEditar};
		
		var response = $http.post("http://localhost:8080/desafio-ayty/rest/app/alterarBeneficiario", jsonObj);
		
		response.success(function(data){
			
			window.alert(data);
			$scope.listarTodos();
			$scope.mostrar = false;
		});
		response.error(function(data) {
			window.alert(data);
			$scope.mostrar = false;
		});
	}
	
	$scope.esconderCampoEditar = function (){
		$scope.mostrar = false;
	}
	
	
	
});
