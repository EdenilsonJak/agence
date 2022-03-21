package com.agence.frota.entities.dto;

import java.io.Serializable;
import java.util.Date;

import com.agence.frota.entities.Carro;
import com.agence.frota.entities.Funcionario;
import com.agence.frota.entities.Viagem;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"usuarioId","carroId","dataRetirada","dataEntrega"})
public class ViagemDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer usuarioId;
	private Integer carroId;
	private Date dataRetirada;
	private Date dataEntrega;
	
	
	public ViagemDto(Viagem viagem) {
		this.id = viagem.getId();
		this.usuarioId = viagem.getFuncionario().getId();
		this.carroId = viagem.getCarro().getId();
		this.dataRetirada = viagem.getDataRetirada();
		this.dataEntrega = viagem.getDataEntrega();
	}
	
	public static Viagem convert(ViagemDto dto) {
		var viagem = new Viagem();
		viagem.setId(dto.getId());
		viagem.setDataEntrega(dto.getDataEntrega());
		viagem.setDataRetirada(dto.getDataRetirada());;
		viagem.setCarro(new Carro(dto.getCarroId(), null, null, null, null));
		viagem.setFuncionario(new Funcionario(dto.getUsuarioId(), null, null));
		return viagem;
	}
	
}
