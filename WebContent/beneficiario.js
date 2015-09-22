var obterCampoSelecionado = function(){
	
	var campo = document.getElementById("opcoes");
	var lol = campo.options[campo.options.selectedIndex];
	window.alert("Opção escolhida: "+lol.text);
	
	if(lol.text === "Cadastrar Beneficiário"){
		window.open("cadastrar-beneficiario.html","_self");
	}else if(lol.text === "Consultar Beneficiário"){
		window.open("consultar-beneficiario.html","_self");
	}else if(lol.text === "Atualizar Beneficiário"){
		window.open("atualizar-beneficiario.html","_self");
	}else{
		window.open("remover-beneficiario.html","_self");
	}
	
};
	
	


