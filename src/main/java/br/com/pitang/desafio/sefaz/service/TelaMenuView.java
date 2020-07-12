package br.com.pitang.desafio.sefaz.service;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.pitang.desafio.sefaz.model.Usuario;

@ManagedBean
@SessionScoped
public class TelaMenuView {

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioLogadoService usuarioLogadoService;

	private String estadoTela;// Inserir, Editar, Buscar
	
	private Usuario novoUsuario;
	

	// Metodos para controle da tela
	public boolean isInserir() {
		return "inserir".equals(estadoTela);
	}

	public boolean isEditar() {
		return "editar".equals(estadoTela);
	}

	public boolean isBuscar() {
		return "buscar".equals(estadoTela);
	}

	public void mudarParaInserir() {
		estadoTela = "inserir";
	}

	public void mudarParaEditar() {
		estadoTela = "editar";
	}

	public void mudarParaBuscar() {
		estadoTela = "buscar";
	}

	
	public void novoUsuario() {
		novoUsuario= new Usuario();
		mudarParaInserir();
	}
	
	
	public void editar(Usuario usuario) {
		novoUsuario = usuario;
		mudarParaEditar();
	}
	
	public void excluirUsuario(Usuario usuario) {
		FacesMessage message;
		try {
			usuarioLogadoService.excluir(usuario);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Usuario excluído com sucesso.");
		} catch (Exception e) {
			
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Erro na exclusão do usuário");
		}

		facesContext.addMessage(null, message);
		mudarParaBuscar();
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

}
