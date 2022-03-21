package com.agence.frota.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agence.frota.entities.Carro;
import com.agence.frota.entities.dto.CarroDto;
import com.agence.frota.entities.enums.Status_Carros;
import com.agence.frota.repositories.CarroRepository;
import com.agence.frota.services.exceptions.DatabaseExceptions;
import com.agence.frota.services.exceptions.ResourceNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	@Transactional(readOnly = true)
	public List<CarroDto> findAll(){
		List<Carro> list = carroRepository.findAll();
		return list.stream().map(c -> new CarroDto(c)).collect(Collectors.toList());
	}

	public void delete(Integer idCarro) {
		try {
			carroRepository.deleteById(idCarro);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Carro não existente.");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseExceptions("Integridade violada.");
		}
	}

	@Transactional
	public CarroDto salvar(CarroDto carroDto) {
		var carro = CarroDto.convert(carroDto);
		carro.setStatus(Status_Carros.DISPONIVEL);
		return new CarroDto(carroRepository.save(carro));
	}
	
	@Transactional
	public void carUpdateStatus(Integer carroId, String status) {
		var car = carroRepository.findById(carroId);
		if(car.isPresent()) {
			car.get().setStatus(Status_Carros.valueOf(status.toUpperCase()));
			carroRepository.save(car.get());
		}
	}
	
	
	@Transactional(readOnly = true)
	public boolean carDisponivel(Integer carroId) {
		var car = carroRepository.findById(carroId);
		return car.get().getStatus() == Status_Carros.DISPONIVEL ? true : false;
	}
	
	@Transactional(readOnly = true)
	public boolean carExistente(Integer carroId) {
		return carroRepository.findById(carroId).isPresent();
	}

	@Transactional(readOnly = true)
	public List<CarroDto> findAllIndisponivel(String status) {
		List<Carro> list = carroRepository.findByStatusContaining(status);
		return list.stream().map(c -> new CarroDto(c)).collect(Collectors.toList());
	}
	
}
