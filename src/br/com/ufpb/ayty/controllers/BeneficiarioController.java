package br.com.ufpb.ayty.controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.ufpb.ayty.dao.UsuarioJpaController;
import br.com.ufpb.ayty.entity.Beneficiario;

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
}
