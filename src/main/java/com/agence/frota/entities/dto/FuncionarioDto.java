package com.agence.frota.entities.dto;

import java.io.Serializable;

import com.agence.frota.entities.Funcionario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"nome", "matricula"})
public class FuncionarioDto implements Serializable {

	/**
	 * Edenilson Mendonca
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Integer matricula;
	
	public FuncionarioDto(Funcionario func) {
		this.id = func.getId();
		this.nome = func.getNome();
		this.matricula = func.getMatricula();
	}
	
	public static Funcionario convert(FuncionarioDto dto) {
		var funcionario = new Funcionario();
		funcionario.setId(dto.getId());
		funcionario.setNome(dto.getNome());
		funcionario.setMatricula(dto.getMatricula());
		return funcionario;
	}

}
