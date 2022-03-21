package com.agence.frota.entities.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.agence.frota.entities.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"username"})
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	
	Set<RoleDto> roles = new HashSet<>();

	public UserDto(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		user.getRoles().forEach(r -> roles.add(new RoleDto(r)));
	}
	
	public static User convert(UserDto dto) {
		var user = new User();
		user.setId(dto.getId());
		user.setUsername(dto.getUsername());
		return user;
	}
	
}
