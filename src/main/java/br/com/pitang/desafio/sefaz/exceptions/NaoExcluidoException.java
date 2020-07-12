package br.com.pitang.desafio.sefaz.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class NaoExcluidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NaoExcluidoException (String mensagem) {
		super(mensagem);
	}
	
	

}
