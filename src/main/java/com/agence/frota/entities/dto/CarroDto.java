package com.agence.frota.entities.dto;

import java.io.Serializable;
import java.sql.Date;

import com.agence.frota.entities.Carro;
import com.agence.frota.entities.enums.Status_Carros;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"modelo","marca","dataFabricacao"})
public class CarroDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String modelo;
	private String marca;
	private Date dataFabricacao;
	private String status;
	
	public CarroDto(Carro carro) {
		this.id = carro.getId();
		this.modelo = carro.getModelo();
		this.marca = carro.getMarca();
		this.dataFabricacao = carro.getDataFabricacao();
		this.status = carro.getStatus().name();
	}
	
	public static Carro convert(CarroDto dto) {
		var carro = new Carro();
		carro.setId(dto.getId());
		carro.setDataFabricacao(dto.getDataFabricacao());
		carro.setMarca(dto.getMarca());
		carro.setModelo(dto.getModelo());
		if(dto.getStatus() != null) {
			carro.setStatus(Status_Carros.valueOf(dto.getStatus()));
		}
		return carro;
	}
	
}
