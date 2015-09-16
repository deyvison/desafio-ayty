package br.com.ufpb.ayty;

public class GerenciarBD {
	
	private BancoPostgre banco;

	public GerenciarBD(){
		banco = new BancoPostgre();
		
	}
	
	public String validarUsuario(String nome, String senha){
		return this.banco.validarUsuario(nome, senha);
	}
	
	public String cadastrarBeneficiario(String nome,String estado_civil,String data_de_nascimento,String nacionalidade,String estado_nasc,String cidade_nasc,String sexo,String cpf,String rg){
		return this.banco.cadastrarBeneficiario(nome, estado_civil, data_de_nascimento, nacionalidade, estado_nasc, cidade_nasc, sexo, cpf, rg);
	}
}

