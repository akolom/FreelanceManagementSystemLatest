package com.ea.neon.dto;

import java.util.List;

import com.ea.neon.domain.Category;
import com.ea.neon.domain.Skills;

public class ProjectSearchDTO {

	private Category category;
	private List<Skills> skills;
	private Double minBudget;
	private Double maxBudget;
	
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
	public Double getMinBudget() {
		return minBudget;
	}
	public void setMinBudget(Double minBudget) {
		this.minBudget = minBudget;
	}
	public Double getMaxBudget() {
		return maxBudget;
	}
	public void setMaxBudget(Double maxBudget) {
		this.maxBudget = maxBudget;
	}

}
