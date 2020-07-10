package br.com.pitang.desafio.sefaz.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class SenhaInválidaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SenhaInválidaException (String mensagem) {
		super(mensagem);
	}
	
	

}
