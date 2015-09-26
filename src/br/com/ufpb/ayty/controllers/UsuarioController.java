package br.com.ufpb.ayty.controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.ufpb.ayty.dao.UsuarioJpaController;
import br.com.ufpb.ayty.entity.Usuario;
import br.com.ufpb.ayty.exception.UsuarioInexistenteException;

public class UsuarioController {
	
	
	private static UsuarioController singleton; // padrão de projeto instancia única
	private String unidadeDeArmazenamento;
	
	
	public UsuarioController(){
		this.unidadeDeArmazenamento = "aytyUsuarios";
	}
	
	public static UsuarioController getInstance() {
		
		if (singleton == null){
			singleton = new UsuarioController();
		}
		return singleton;
	}
	
	
	public void cadastrarUsuario(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
		UsuarioJpaController ujc = new UsuarioJpaController(emf);

		try {
			ujc.createUsuario(usuario);

		} finally {
			emf.close();
		}

	}
	
	public boolean validarUsuario(String login, String senha) throws UsuarioInexistenteException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
		UsuarioJpaController ujc = new UsuarioJpaController(emf);
		boolean resposta;

		try {
			resposta = ujc.validarUsuario(login, senha);

		} finally {
			emf.close();
		}

		return resposta;
	}
	
}
