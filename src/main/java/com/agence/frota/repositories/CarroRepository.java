package com.agence.frota.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agence.frota.entities.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{
	
	@Query(value = "SELECT * FROM tb_carro where status =:status", nativeQuery=true)
	public List<Carro> findByStatusContaining(@Param("status")String status);

}
