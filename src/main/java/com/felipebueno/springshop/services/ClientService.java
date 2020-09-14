package com.felipebueno.springshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipebueno.springshop.domain.Client;
import com.felipebueno.springshop.payloads.ClientPayload;
import com.felipebueno.springshop.repositories.ClientRepository;
import com.felipebueno.springshop.services.exceptions.DataIntegrityException;
import com.felipebueno.springshop.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Client find(Integer id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Object with id: " + id + 
				" not found, Type: " + Client.class.getName()));
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui pedidos ou endereços cadastrados");
		}
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	public Client fromPayload(ClientPayload payload) {
		return new Client(payload.getId(), payload.getName(), payload.getEmail(), null, null);
	}
	
	private Client updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
		return newObj;
	}
	
}
