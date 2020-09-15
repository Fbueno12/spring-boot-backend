package com.felipebueno.springshop.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipebueno.springshop.domain.Address;
import com.felipebueno.springshop.domain.City;
import com.felipebueno.springshop.domain.Client;
import com.felipebueno.springshop.domain.enums.ClientType;
import com.felipebueno.springshop.payloads.ClientPayload;
import com.felipebueno.springshop.payloads.NewClientPayload;
import com.felipebueno.springshop.repositories.AddressRepository;
import com.felipebueno.springshop.repositories.ClientRepository;
import com.felipebueno.springshop.services.exceptions.DataIntegrityException;
import com.felipebueno.springshop.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Object with id: " + id + " not found, Type: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repository.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
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
			throw new DataIntegrityException(
					"Não é possivel excluir um cliente que possui pedidos ou endereços cadastrados");
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

	public Client fromPayload(NewClientPayload payload) {
		Client cli = new Client(null, payload.getName(), payload.getEmail(), payload.getDocument(),
				ClientType.toEnum(payload.getClientType()));

		City city = new City(payload.getCidade_id(), null, null);

		Address end = new Address(null, payload.getStreet(), payload.getNumber(), payload.getComplement(),
				payload.getNeighborhood(), payload.getStreetCode(), cli, city);

		cli.getAddresses().add(end);
		cli.getTelephones().add(payload.getTelefone1());
		if (payload.getTelefone2() != null) {
			cli.getTelephones().add(payload.getTelefone2());
		}
		if (payload.getTelefone3() != null) {
			cli.getTelephones().add(payload.getTelefone3());
		}

		return cli;
	}

	private Client updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());

		return newObj;
	}

}
