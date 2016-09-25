package com.ea.neon.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "employeer")
public class Employer extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5506187625983921769L;
	
	private Integer projectCompleted;

	@OneToMany(mappedBy = "employer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Project> project;

	public Integer getProjectCompleted() {
		return projectCompleted;
	}

	public void setProjectCompleted(Integer projectCompleted) {
		this.projectCompleted = projectCompleted;
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

}
