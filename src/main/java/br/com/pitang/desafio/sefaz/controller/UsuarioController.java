/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.pitang.desafio.sefaz.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pitang.desafio.sefaz.model.Usuario;
import br.com.pitang.desafio.sefaz.service.UsuarioLogadoService;
import br.com.pitang.desafio.sefaz.service.UsuarioLogin;

@Model
public class UsuarioController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioLogadoService usuarioLogadoService;

	@Inject
	private UsuarioLogin usuarioLogin;

	@Produces
	@Named
	private Usuario novoUsuario;

	@PostConstruct
	public void initNewMember() {
		novoUsuario = new Usuario();
	}

	public String validar() throws Exception {
		try {
			usuarioLogin.validar(novoUsuario);
			return "usuarioLogado?faces-redirect=true";
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
					"Erro na validação do usuário");
			facesContext.addMessage(null, message);
			return errorMessage;
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Validação falhou";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

}
