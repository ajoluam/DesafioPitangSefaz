package br.com.pitang.desafio.sefaz.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class NaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
	

}
