package br.com.ufpb.ayty.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="beneficiarios")
public class Beneficiario {
	
	@Id
	private String cpf;
	
	private String nome, estado_civil, data_nascimento, nacionalidade, estado_nascimento, cidade_nascimento, sexo, rg;
	
	
	public Beneficiario(String nome, String estado_civil, String data_nascimento, String nacionalidade, String estado_nascimento,
		String cidade_nascimento, String sexo, String cpf, String rg){
	
		this.nome = nome;
		this.estado_civil = estado_civil;
		this.data_nascimento = data_nascimento;
		this.nacionalidade = nacionalidade;
		this.estado_nascimento = estado_nascimento;
		this.cidade_nascimento = cidade_nascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.rg = rg;
	}

	public Beneficiario(){
		
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEstado_civil() {
		return estado_civil;
	}


	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}


	public String getData_nascimento() {
		return data_nascimento;
	}


	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}


	public String getNacionalidade() {
		return nacionalidade;
	}


	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}


	public String getEstado_nascimento() {
		return estado_nascimento;
	}


	public void setEstado_nascimento(String estado_nascimento) {
		this.estado_nascimento = estado_nascimento;
	}


	public String getCidade_nascimento() {
		return cidade_nascimento;
	}


	public void setCidade_nascimento(String cidade_nascimento) {
		this.cidade_nascimento = cidade_nascimento;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Beneficiario [cpf=" + cpf + ", nome=" + nome + ", estado_civil=" + estado_civil + ", data_nascimento="
				+ data_nascimento + ", nacionalidade=" + nacionalidade + ", estado_nascimento=" + estado_nascimento
				+ ", cidade_nascimento=" + cidade_nascimento + ", sexo=" + sexo + ", rg=" + rg + "]";
	}
	
	
	
}
