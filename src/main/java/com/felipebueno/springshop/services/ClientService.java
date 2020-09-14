package com.felipebueno.springshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.springshop.domain.Client;
import com.felipebueno.springshop.repositories.ClientRepository;

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
	
}
