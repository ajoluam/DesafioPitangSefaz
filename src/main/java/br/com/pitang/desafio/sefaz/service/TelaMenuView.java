package br.com.pitang.desafio.sefaz.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.pitang.desafio.sefaz.model.Telefone;
import br.com.pitang.desafio.sefaz.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class TelaMenuView {

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioLogadoService usuarioLogadoService;

	private String estadoTela;// Inserir, Editar, Buscar
	
	private Usuario novoUsuario;
	
	private Telefone novoTelefone;
	
	private List<Usuario> listaUsuarios;
	

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
		listaUsuarios.remove(usuario);
		try {
			usuarioLogadoService.excluir(usuario);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Usuario excluído com sucesso.");
		} catch (Exception e) {
			
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Erro na exclusão do usuário");
		}

		facesContext.addMessage(null, message);
		mudarParaBuscar();
	}

	public void excluirTelefone(Telefone telefone) {
		novoUsuario.getTelefones().remove(telefone);
		FacesMessage message;
		try {
			usuarioLogadoService.excluiTelefone(telefone);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Telefone excluído com sucesso.");
		} catch (Exception e) {
			
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Erro na exclusão do telefone");
		}

		facesContext.addMessage(null, message);
		mudarParaEditar();

		
	}
	
	public void buscarUsuarios() {
		listaUsuarios = usuarioLogadoService.listarUsuarios();
		mudarParaBuscar();
	}
	


}
