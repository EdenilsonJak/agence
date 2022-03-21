package com.agence.frota.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agence.frota.entities.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Integer>{

	@Query(value = "SELECT * FROM TB_VIAGEM WHERE EXTRACT (year FROM (SELECT DATA_RETIRADA)) =:ano AND EXTRACT (month FROM (SELECT DATA_RETIRADA)) =:mes AND DATA_ENTREGA IS NOT NULL", nativeQuery = true)
	public List<Viagem> findByViagensConcluidas(@Param("ano") String ano, @Param("mes") String mes);

}
