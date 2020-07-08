package br.com.pitang.desafio.sefaz.util;

public enum TelefoneTipo {
	
	CELULAR("Celular"), 
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial"), 
	OUTROS("Outros");
	
	private String descricao;

	private TelefoneTipo(String descricao) {
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
		
}
