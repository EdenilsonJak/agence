package com.agence.frota.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agence.frota.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	
}
