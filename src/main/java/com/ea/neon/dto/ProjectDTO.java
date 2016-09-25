package com.ea.neon.dto;

import java.util.List;

import com.ea.neon.domain.Category;
import com.ea.neon.domain.Skills;

public class ProjectDTO {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private Double budget;
	
	private Category category;
	
	private List<Skills> skills;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}
	
}
