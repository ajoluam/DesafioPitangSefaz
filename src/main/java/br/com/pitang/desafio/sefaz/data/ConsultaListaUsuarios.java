package br.com.pitang.desafio.sefaz.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pitang.desafio.sefaz.model.Usuario;

@RequestScoped
public class ConsultaListaUsuarios {
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	private List<Usuario> usuarioList;
	
	@Produces
	@Named
	public List<Usuario> getUsuarioList(){
		return usuarioList;
	}
	
	public void onUsuarioListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Usuario usuario) {
		retrieveAllUsersOrderedByName();

	}

	@PostConstruct
	public void retrieveAllUsersOrderedByName() {
		usuarioList = usuarioRepository.findAllOrderedByName();
	}

}
