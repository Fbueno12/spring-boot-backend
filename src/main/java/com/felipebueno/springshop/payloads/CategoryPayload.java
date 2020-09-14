package com.felipebueno.springshop.payloads;

import java.io.Serializable;

import com.felipebueno.springshop.domain.Category;

public class CategoryPayload implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

	public CategoryPayload() {
	}
	
	public CategoryPayload(Category obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
