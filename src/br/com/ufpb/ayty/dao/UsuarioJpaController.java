package br.com.ufpb.ayty.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ufpb.ayty.entity.Beneficiario;
import br.com.ufpb.ayty.entity.Usuario;
import br.com.ufpb.ayty.exception.UsuarioInexistenteException;

public class UsuarioJpaController {

	private EntityManagerFactory emf = null;

	public UsuarioJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void createUsuario(Usuario usuario) {

		EntityManager em = null;
		try {

			em = getEntityManager();
			em.getTransaction().begin();

			em.persist(usuario);
			em.getTransaction().commit();

		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public boolean validarUsuario(String login, String senha) throws UsuarioInexistenteException {
		EntityManager em = null;

		try {
			em = getEntityManager();

			Usuario result = pesquisarUsuarioPorLogin(login);

			if (result.getLogin().equals(login) && result.getSenha().equals(senha)) {
				return true;
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}

		return false;
	}

	public Usuario pesquisarUsuarioPorLogin(String login) throws UsuarioInexistenteException {
		EntityManager em = null;
		try {
			em = getEntityManager();

			if (!existeUsuario(login)) {
				throw new UsuarioInexistenteException("Usuário de login " + login + " não está cadastrado!");
			}

			return em.find(Usuario.class, login);
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public boolean existeUsuario(String login) {
		Object result = null;
		EntityManager em = null;

		try {
			em = getEntityManager();
			Query query = em.createQuery("SELECT 1 FROM Usuario WHERE login = ?");
			query.setParameter(1, login);
			query.setMaxResults(1);

			result = query.getSingleResult();
		} catch (NoResultException e) {
			result = null;
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return (result != null);
	}

	public void createBeneficiario(Beneficiario beneficiario) {

		EntityManager em = null;
		try {

			em = getEntityManager();
			em.getTransaction().begin();

			em.persist(beneficiario);
			em.getTransaction().commit();

		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
