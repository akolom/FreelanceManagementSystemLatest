package com.ea.neon.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "freelancer")
public class Freelancer extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2984456787460142966L;

	private Double charge;


	private Double jobCompleted;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "freelancer_experiances")
	private List<Experience> experiances;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "freelancer_education")
	private List<Education> educations;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable
	private List<Project> projects;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private List<Certifications> certifications;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn
	private List<Skills> skills;

	public List<Experience> getExperiances() {
		return experiances;
	}

	public void setExperiances(List<Experience> experiances) {
		this.experiances = experiances;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getJobCompleted() {
		return jobCompleted;
	}

	public List<Certifications> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Certifications> certifications) {
		this.certifications = certifications;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public void setJobCompleted(Double jobCompleted) {
		this.jobCompleted = jobCompleted;
	}

}
