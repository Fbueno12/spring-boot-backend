package com.felipebueno.springshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.springshop.domain.Demand;
import com.felipebueno.springshop.repositories.DemandRepository;

import com.felipebueno.springshop.services.exceptions.ObjectNotFoundException;

@Service
public class DemandService {
	
	@Autowired
	private DemandRepository repository;
	
	public Demand find(Integer id) {
		Optional<Demand> demand = repository.findById(id);
		return demand.orElseThrow(() -> new ObjectNotFoundException("Object with id: " + id + 
				" not found, Type: " + Demand.class.getName()));
	}	
	
}
