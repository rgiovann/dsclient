package com.devsuperior.dsclient.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsclient.dto.ClientDto;
import com.devsuperior.dsclient.entities.Client;
import com.devsuperior.dsclient.repositories.ClientRepository;
import com.devsuperior.dsclient.services.exceptions.DatabaseException;
import com.devsuperior.dsclient.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
			
	public ClientService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		// Page already is an stream since Java 8.X, noo need to convert
		return list.map(p -> new ClientDto(p));

	}	
	
	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Could not find id: " + id));
		return new ClientDto(entity);
	}
	
	@Transactional
	public ClientDto insert(ClientDto clientDto) {
		Client entity = new Client();
		copyDtoToEntity(clientDto,entity);
		entity = repository.save(entity);  
		return new ClientDto(entity);
	}	
	
	
	@Transactional
	public ClientDto update(Long id, ClientDto clientDto) {
		try {
			
			Client proxyEntity = repository.getReferenceById(id);
			copyDtoToEntity(clientDto,proxyEntity);
			return new ClientDto(proxyEntity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Could not find id: " + id);
		}
	}	
	
		
	private void copyDtoToEntity(ClientDto dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());	
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Could not find id: "  + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Error. Integrity violation: " + id);
		}

	}
}
	
 
