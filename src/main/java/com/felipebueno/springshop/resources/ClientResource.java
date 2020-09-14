package com.felipebueno.springshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipebueno.springshop.domain.Client;
import com.felipebueno.springshop.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}" )
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		Client client = service.find(id);
		
		return ResponseEntity.ok().body(client);
	}
	
}
