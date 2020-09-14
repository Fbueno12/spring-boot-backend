package com.felipebueno.springshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipebueno.springshop.domain.Category;
import com.felipebueno.springshop.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}" )
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category category = service.find(id);
		
		return ResponseEntity.ok().body(category);
	}
	
}
