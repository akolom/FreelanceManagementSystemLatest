package com.ea.neon.service;

import java.util.List;

import com.ea.neon.domain.Category;

public interface CategoryService {

	public List<Category> findAll();

	public Category getCategoryById(int id);
	
}
