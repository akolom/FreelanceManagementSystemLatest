package com.ea.neon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6613657980527314974L;

	@Id
	@GeneratedValue
	private Integer id;

	public enum ProjectStatus {
		CALL_FOR_INTERVIEW("Call For Interview"), DECLINED("Declined"), PENDING("Pending"), ACCEPTED("Accepted");
		private final String text;

		private ProjectStatus(final String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return text;
		}

		public static ProjectStatus fromString(String text) {
			if (text != null) {
				for (ProjectStatus b : ProjectStatus.values()) {
					if (text.equalsIgnoreCase(b.text)) {
						return b;
					}
				}
			}
			return null;
		}
	};

	private ProjectStatus projectStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

}
