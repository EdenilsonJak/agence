package com.agence.frota.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agence.frota.entities.Funcionario;
import com.agence.frota.entities.dto.FuncionarioDto;
import com.agence.frota.repositories.FuncionarioRepository;
import com.agence.frota.services.exceptions.DatabaseExceptions;
import com.agence.frota.services.exceptions.ResourceNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Transactional(readOnly = true)
	public List<FuncionarioDto> findAll(){
		List<Funcionario> list = funcionarioRepository.findAll();
		return list.stream().map(f -> new FuncionarioDto(f)).collect(Collectors.toList());
	}

	public void delete(Integer idFuncionario) {
		try {
			funcionarioRepository.deleteById(idFuncionario);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Funcionário nao existente.");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseExceptions("Integridade violada.");
		}
	}

	@Transactional
	public FuncionarioDto salvar(FuncionarioDto funcDto) {
		var funcionario = FuncionarioDto.convert(funcDto);
		return new FuncionarioDto(funcionarioRepository.save(funcionario));
	}
	
	@Transactional(readOnly = true)
	public boolean funcExistente(Integer funcionarioId) {
		return funcionarioRepository.findById(funcionarioId).isPresent();
	}
	
}
