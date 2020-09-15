package com.felipebueno.springshop.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipebueno.springshop.domain.Client;
import com.felipebueno.springshop.payloads.ClientPayload;
import com.felipebueno.springshop.payloads.NewClientPayload;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NewClientPayload payload) {
		Client obj = service.fromPayload(payload);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClientPayload payload) {
		Client obj = service.fromPayload(payload);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClientPayload>> findAll() {
		List<Client> categories = service.findAll();
		List<ClientPayload> payload = categories.stream().map(obj -> new ClientPayload(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(payload);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public ResponseEntity<Page<ClientPayload>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Client> categories = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientPayload> payload = categories.map(obj -> new ClientPayload(obj));

		return ResponseEntity.ok().body(payload);
	}
	
}
