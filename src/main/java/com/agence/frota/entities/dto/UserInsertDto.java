package com.agence.frota.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInsertDto extends UserDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String password;
	
	public UserInsertDto() {
		super();
	}
}
