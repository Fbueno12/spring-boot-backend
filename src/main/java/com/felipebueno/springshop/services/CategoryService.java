package com.felipebueno.springshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipebueno.springshop.domain.Category;
import com.felipebueno.springshop.payloads.CategoryPayload;
import com.felipebueno.springshop.repositories.CategoryRepository;
import com.felipebueno.springshop.services.exceptions.DataIntegrityException;
import com.felipebueno.springshop.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category find(Integer id) {
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Object with id: " + id + " not found, Type: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Category update(Category obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!");
		}
	}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	public Category fromPayload(CategoryPayload payload) {
		return new Category(payload.getId(), payload.getName());
	}
}
