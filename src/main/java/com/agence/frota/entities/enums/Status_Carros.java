package com.agence.frota.entities.enums;

public enum Status_Carros {
	
	INDISPONIVEL("INDISPONÍVEL"),
	DISPONIVEL("DISPONÍVEL");
	
	private String descricao;
	
	private Status_Carros(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
