package com.agence.frota.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agence.frota.entities.dto.FuncionarioDto;
import com.agence.frota.services.FuncionarioService;

@RestController
@RequestMapping(value = "/api/v1/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDto>> findAll(){
		List<FuncionarioDto> funcionarios = funcionarioService.findAll();
		return ResponseEntity.ok().body(funcionarios);
	}
	
	@PostMapping
	public ResponseEntity<FuncionarioDto> insert(@RequestBody FuncionarioDto func){
		func = funcionarioService.salvar(func);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
				 .buildAndExpand(func.getId()).toUri();
		return ResponseEntity.created(uri).body(func);
	}
	
	@DeleteMapping(value = "/{idFuncionario}")
	public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer idFuncionario){
		funcionarioService.delete(idFuncionario);
		return ResponseEntity.noContent().build();
	}

}
