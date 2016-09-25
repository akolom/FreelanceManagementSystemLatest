package com.ea.neon.dto;

import java.io.Serializable;

import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;

public class ProjectApplyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4485239993272353660L;

	private Project project;
	
	private Freelancer freelancer;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}
	
	
}
