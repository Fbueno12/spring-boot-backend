package com.felipebueno.springshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.springshop.domain.Category;
import com.felipebueno.springshop.repositories.CategoryRepository;

import com.felipebueno.springshop.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category find(Integer id) {
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Object with id: " + id + 
				" not found, Type: " + Category.class.getName()));
	}
	
}
