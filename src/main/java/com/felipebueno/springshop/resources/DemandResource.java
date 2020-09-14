package com.felipebueno.springshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipebueno.springshop.domain.Demand;
import com.felipebueno.springshop.services.DemandService;

@RestController
@RequestMapping(value = "/demands")
public class DemandResource {

	@Autowired
	private DemandService service;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Demand> find(@PathVariable Integer id) {
		Demand demand = service.find(id);

		return ResponseEntity.ok().body(demand);
	}

}
