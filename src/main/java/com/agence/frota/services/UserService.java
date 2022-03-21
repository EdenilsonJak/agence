package com.agence.frota.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agence.frota.entities.User;
import com.agence.frota.entities.dto.UserDto;
import com.agence.frota.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public UserDto salvar(UserDto userDto) {
		var user = UserDto.convert(userDto);
		return new UserDto(userRepository.save(user));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			logger.error("User not found " + username);
			throw new UsernameNotFoundException("User not found");
		}
		logger.info("User found: "+ username);
		return user;
	}
	
}
