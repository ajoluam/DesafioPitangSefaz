package br.com.pitang.desafio.sefaz.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.pitang.desafio.sefaz.exceptions.NaoEncontradoException;
import br.com.pitang.desafio.sefaz.model.Usuario;

@ApplicationScoped
public class UsuarioRepository {

	@Inject
	private EntityManager em;

	public boolean validacaoUsuario(final String email, final String senha) {
		Usuario usuario = this.findByEmail(email);
		return senha == usuario.getSenha();

	}

	public Usuario findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> usuario = criteria.from(Usuario.class);
		criteria.select(usuario).where(cb.equal(usuario.get("email"), email));
		Usuario usuarioDB = em.createQuery(criteria).getSingleResult();

		if (usuarioDB == null) {
			throw new NaoEncontradoException("Usuario não encontrado no Banco.");
		} else
			return usuarioDB;
	}

	public List<Usuario> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> usuario = criteria.from(Usuario.class);
		criteria.select(usuario).orderBy(cb.asc(usuario.get("nome")));
		return em.createQuery(criteria).getResultList();
	}

}
