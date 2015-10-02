package br.com.ufpb.ayty.controllers;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.ufpb.ayty.dao.UsuarioJpaController;
import br.com.ufpb.ayty.entity.Beneficiario;
import br.com.ufpb.ayty.exception.BeneficiarioInexistenteException;

public class BeneficiarioController {

	private static BeneficiarioController singleton; // padrão de projeto // instancia única
	private String unidadeDeArmazenamento;

	public BeneficiarioController() {
		this.unidadeDeArmazenamento = "aytyUsuarios";
	}

	public static BeneficiarioController getInstance() {

		if (singleton == null) {
			singleton = new BeneficiarioController();
		}
		return singleton;
	}
	
	public void cadastrarBeneficiario(Beneficiario beneficiario){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
		UsuarioJpaController ujc = new UsuarioJpaController(emf);

		try {
			ujc.createBeneficiario(beneficiario);

		} finally {
			emf.close();
		}
	}
	
	public Beneficiario pesquisarBeneficiario(String cpf) throws BeneficiarioInexistenteException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
		UsuarioJpaController ujc = new UsuarioJpaController(emf);
		
		Beneficiario b = null;
		
		b = ujc.pesquisarBeneficiario(cpf);
		return b;
		
		
	}
	
	public Beneficiario removerBeneficiario(String cpf){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
		UsuarioJpaController ujc = new UsuarioJpaController(emf);
		
		Beneficiario b = null;
		
		b = ujc.removerBeneficiario(cpf);
		return b;
	}

	public List<Beneficiario> listarTodosBeneficiarios() {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
		UsuarioJpaController ujc = new UsuarioJpaController(emf);
		
		return ujc.listAll();
	}

	public Beneficiario alterarBeneficiario(Beneficiario b) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
		UsuarioJpaController ujc = new UsuarioJpaController(emf);
		
		return ujc.update(b);
		
	}
}
