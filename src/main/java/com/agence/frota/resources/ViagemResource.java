package com.agence.frota.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agence.frota.entities.dto.ViagemDto;
import com.agence.frota.services.ViagemService;

@RestController
@RequestMapping(value = "/api/v1/viagem")
public class ViagemResource {
	
	@Autowired
	private ViagemService viagemService;
	
	
	@PostMapping(value = "/{idFuncionario}/{idCarro}")
	public ResponseEntity<ViagemDto> retiradaVeiculo(@PathVariable Integer idFuncionario, @PathVariable Integer idCarro){
		var viagem = viagemService.retiradaVeiculo(idFuncionario, idCarro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
				 .buildAndExpand(viagem.getId()).toUri();
		return ResponseEntity.ok().body(viagem);
	}
	
	@DeleteMapping(value = "/{idFuncionario}/{idCarro}/{idViagem}")
	public ResponseEntity<ViagemDto> devolucaoVeiculo(@PathVariable Integer idFuncionario, @PathVariable Integer idCarro, @PathVariable Integer idViagem){
		var viagem = viagemService.devolucaoVeiculo(idFuncionario, idCarro, idViagem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
				 .buildAndExpand(viagem.getId()).toUri();
		return ResponseEntity.ok().body(viagem);
	}
	
	@GetMapping(value = "/{mes}/{ano}")
	public ResponseEntity<List<ViagemDto>> consultaViagensConcluidas(@PathVariable String mes, @PathVariable String ano){
		return ResponseEntity.ok().body(viagemService.viagensConcluidasAll(ano, mes));
	}

}
