package com.ea.neon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skills implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6779910109074703397L;

	@Id
	@GeneratedValue
	private Integer id;
	
	public enum SkillTitle{
		JAVA("JAVA"), HTML_HTML5("HTML/HTML5"), PHP("PHP"), JAVASCRIPT("JAVAScript"), 
		MYSQL("MySQL"), C_PROGRAMMING("C Programming"), JQUERY("JQuery"),C_PLUS_PLUS("C++"), 
		C_SHARP("C#"), PYTHON("Python"), ANDROID("Android"), GRAPHIC_DESIGN("Graphic Design"), SPRING_MVC("Spring MVC"); 
		private final String text;
		private SkillTitle(final String text){
			this.text = text;
		}
		
		
		public String getText() {
			return text;
		}


		@Override
		public String toString() {
			return text;
		}
		
		public static SkillTitle fromString(String text) {
		    if (text != null) {
		      for (SkillTitle b : SkillTitle.values()) {
		        if (text.equalsIgnoreCase(b.text)) {
		          return b;
		        }
		      }
		    }
		    return null;
		  }

	};

	private SkillTitle skillTitle;

	public SkillTitle getSkillTitle() {
		return skillTitle;
	}

	public void setSkillTitle(SkillTitle skillTitle) {
		this.skillTitle = skillTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
