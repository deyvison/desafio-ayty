package br.com.ufpb.ayty.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ufpb.ayty.entity.Beneficiario;
import br.com.ufpb.ayty.entity.Usuario;
import br.com.ufpb.ayty.exception.BeneficiarioInexistenteException;
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
	
	/*
	public boolean existeBeneficiario(String cpf) {
		Object result = null;
		EntityManager em = null;
		
		try {
			em = getEntityManager();
			Query query = em.createQuery("select 1 FROM beneficiarios WHERE cpf = ?");
			query.setParameter(1, cpf);
			query.setMaxResults(1);

			result = query.getSingleResult();
		} catch (Exception e) {
			result = null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return (result != null);
	} **/

	public Beneficiario pesquisarBeneficiario(String cpf) throws BeneficiarioInexistenteException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = getEntityManager();

			//if (!existeBeneficiario(cpf)) {
				//throw new BeneficiarioInexistenteException("Beneficiario de cpf: "+cpf+" não cadastrado!");
			//}
			
			return em.find(Beneficiario.class, cpf);
		}catch(Exception err){
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}

	public Beneficiario removerBeneficiario(String cpf) {
		EntityManager em = null;
		try {
			em = getEntityManager();

			//if (!existeBeneficiario(cpf)) {
				//throw new BeneficiarioInexistenteException("Beneficiario de cpf: "+cpf+" não cadastrado!");
			//}
			
			Beneficiario b = em.find(Beneficiario.class, cpf);
			if(b != null){
				em.remove(b);
			}
			em.getTransaction().begin();
			em.getTransaction().commit();
			return b;
		}catch(Exception err){
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
