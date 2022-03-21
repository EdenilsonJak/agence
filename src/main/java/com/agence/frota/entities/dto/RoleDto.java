package com.agence.frota.entities.dto;

import java.io.Serializable;

import com.agence.frota.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;
	
	public RoleDto(Role role) {
		id = role.getId();
		authority = role.getAuthority();
	}
	
}
