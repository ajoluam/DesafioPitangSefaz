package br.com.pitang.desafio.sefaz.data;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.pitang.desafio.sefaz.exceptions.NaoEncontradoException;
import br.com.pitang.desafio.sefaz.exceptions.NaoExcluidoException;
import br.com.pitang.desafio.sefaz.model.Usuario;

@ApplicationScoped
public class UsuarioRepository {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	public boolean validacaoUsuario(final String email, final String senha) {
		Usuario usuario = this.findByEmail(email);
		return senha == usuario.getSenha();

	}

	public Usuario findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> usuario = criteria.from(Usuario.class);
		criteria.select(usuario).where(cb.equal(usuario.get("email"), email));
		Usuario usuarioDB = null;

		try {

			usuarioDB = em.createQuery(criteria).getSingleResult();

		} catch (final NoResultException e) {
			log.info("Usuario não encontrado no Banco para email:" + email);
			throw new NaoEncontradoException("Usuario não encontrado no Banco.");
		}
		return usuarioDB;
	}

	public List<Usuario> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> usuario = criteria.from(Usuario.class);
		criteria.select(usuario).orderBy(cb.asc(usuario.get("nome")));
		return em.createQuery(criteria).getResultList();
	}

	public boolean excluiUsuario(Usuario usuario) {
		try {
			em.createNativeQuery("DELETE FROM telefone WHERE usuario_id=?").setParameter(1, usuario.getId())
					.executeUpdate();
			em.createNativeQuery("DELETE FROM usuario WHERE id=?").setParameter(1, usuario.getId()).executeUpdate();
			em.flush();

			return true;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new NaoExcluidoException(e.getMessage());
		}

	}

}
