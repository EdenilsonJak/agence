package com.agence.frota.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agence.frota.entities.Carro;
import com.agence.frota.entities.Funcionario;
import com.agence.frota.entities.Viagem;
import com.agence.frota.entities.dto.ViagemDto;
import com.agence.frota.entities.enums.Status_Carros;
import com.agence.frota.repositories.ViagemRepository;
import com.agence.frota.services.exceptions.DatabaseExceptions;
import com.agence.frota.services.exceptions.ResourceNotFoundException;

@Service
public class ViagemService {

	@Autowired
	private ViagemRepository viagemRepository;
	
	@Autowired
	private CarroService carroService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Transactional(readOnly = true)
	public List<ViagemDto> findAll(){
		List<Viagem> list = viagemRepository.findAll();
		return list.stream().map(c -> new ViagemDto(c)).collect(Collectors.toList());
	}

	public void delete(Integer idCarro) {
		try {
			viagemRepository.deleteById(idCarro);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Viagem não existente.");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseExceptions("Integridade violada.");
		}
	}

	@Transactional
	public ViagemDto salvar(ViagemDto viagemDto) {
		var viagem = ViagemDto.convert(viagemDto);
		return new ViagemDto(viagemRepository.save(viagem));
	}

	public ViagemDto retiradaVeiculo(Integer idFuncionario, Integer idCarro) {
		var retiradaSalvo = new Viagem();
		retiradaSalvo.setCarro(new Carro(idCarro, null, null, null, null));
		retiradaSalvo.setFuncionario(new Funcionario(idFuncionario, null, null));
		retiradaSalvo.setDataRetirada(new Date());
	
		if(carroService.carDisponivel(idCarro)) {
			carroService.carUpdateStatus(idCarro, Status_Carros.INDISPONIVEL.name());
		} else {
			throw new ResourceNotFoundException("Carro não disponível.");
		}
		
		return new ViagemDto(viagemRepository.save(retiradaSalvo));
	}

	@Transactional
	public ViagemDto devolucaoVeiculo(Integer idFuncionario, Integer idCarro, Integer idViagem) {
		var devolucaoSalvo = viagemRepository.findById(idViagem);
		
		if(devolucaoSalvo.isPresent()) {
			var devolucao = devolucaoSalvo.get();
			
			if(!(idFuncionario == devolucao.getFuncionario().getId())) {
				throw new ResourceNotFoundException("Funcionario não existente.");	
			}
			
			if(!(idCarro == devolucao.getCarro().getId())) {
				throw new ResourceNotFoundException("Carro não existente.");
			} 
			
			if(!carroService.carDisponivel(idCarro)) {
				carroService.carUpdateStatus(idCarro, Status_Carros.DISPONIVEL.name());
				devolucao.setDataEntrega(new Date());
			} else {
				throw new ResourceNotFoundException("Carro não disponível.");
			}
			
			return new ViagemDto(viagemRepository.save(devolucao));
		
		} else {
			throw new ResourceNotFoundException("Not found.");
		}
		
		
	}
	
	@Transactional(readOnly = true)
	public List<ViagemDto> viagensConcluidasAll(String ano, String mes){
		List<Viagem> list = viagemRepository.findByViagensConcluidas(ano, mes);
		return list.stream().map(v -> new ViagemDto(v)).collect(Collectors.toList());
	}
	
}
