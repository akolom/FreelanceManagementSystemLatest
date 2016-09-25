package com.ea.neon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ea.neon.validation.aspect.EmptyOrSize;

@Entity
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575312824734117454L;

	@Id
	@GeneratedValue
	private Integer id;

	@EmptyOrSize(min=10,max=100,message="{EmptyOrSize}")
	private String professionalHeadLine;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfessionalHeadLine() {
		return professionalHeadLine;
	}

	public void setProfessionalHeadLine(String professionalHeadLine) {
		this.professionalHeadLine = professionalHeadLine;
	}

	public String getProfileSummary() {
		return profileSummary;
	}

	public void setProfileSummary(String profileSummary) {
		this.profileSummary = profileSummary;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@EmptyOrSize(min=10,max=1000,message="{EmptyOrSize}")
	private String profileSummary;

	private Integer rank;
}
