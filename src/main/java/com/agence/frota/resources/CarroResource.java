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

import com.agence.frota.entities.dto.CarroDto;
import com.agence.frota.entities.enums.Status_Carros;
import com.agence.frota.services.CarroService;

@RestController
@RequestMapping(value = "/api/v1/carros")
public class CarroResource {
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<CarroDto>> findAll(){
		List<CarroDto> carros = carroService.findAll();
		return ResponseEntity.ok().body(carros);
	}
	
	@GetMapping(value = "/retirados")
	public ResponseEntity<List<CarroDto>> findAllIndisponivel(){
		List<CarroDto> carros = carroService.findAllIndisponivel(Status_Carros.INDISPONIVEL.name());
		return ResponseEntity.ok().body(carros);
	}
	
	@PostMapping
	public ResponseEntity<CarroDto> insert(@RequestBody CarroDto car){
		car = carroService.salvar(car);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
				 .buildAndExpand(car.getId()).toUri();
		return ResponseEntity.created(uri).body(car);
	}
	
	@DeleteMapping(value = "/{idCarro}")
	public ResponseEntity<Void> deleteCarro(@PathVariable Integer idCarro){
		carroService.delete(idCarro);
		return ResponseEntity.noContent().build();
	}

}
