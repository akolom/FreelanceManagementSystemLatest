package com.ea.neon.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ea.neon.domain.Category;
import com.ea.neon.domain.Employer;
import com.ea.neon.domain.Project;
import com.ea.neon.domain.Skills;
import com.ea.neon.domain.Skills.SkillTitle;
import com.ea.neon.service.CategoryService;
import com.ea.neon.service.ProjectService;
import com.ea.neon.service.SkillService;
import com.ea.neon.service.UserService;

/**
 * @author KESHAV
 * 
 *         Controller to handle every request of an employer
 *
 */
@Controller
@RequestMapping(value = "/employer")
public class EmployerController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private SkillService skillService;

	@ModelAttribute("newProject")
	public Project getProject() {
		return new Project();
	}

	/**
	 * Method to get current user information and bind it to employer's profile
	 * page using model. Uses principal object to get username of current
	 * user(employer)
	 * 
	 * @param model
	 *            Object on which data from database is set as attributes.
	 * @param principal
	 *            Object from which username of current user is get.
	 * @return name of jsp page for employer's profile
	 */
	@RequestMapping(value = "/profile")
	public String viewProfile(Model model, Principal principal) {
		String username = principal.getName();
		Employer employer = (Employer) userService.findOneByUsername(username);
		List<Project> projects = projectService.findAllByEmployer(employer);
		employer.setProject(projects);
		model.addAttribute("currentUser", employer);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("skills", skillService.findAll());

		return "employerProfile";
	}

	/**
	 * Adds the new project or edited project to database. Validates project
	 * before saving to database. Binds the errors of validation to binding
	 * result. If binding result has error, redirect to current form to display
	 * errors.
	 * 
	 * @param project
	 *            Object bound with spring form for adding or updating project.
	 * @param result
	 *            Object which will store errors while validating.
	 * @param principal
	 *            Object from which username of current user is get.
	 * @return redirect to employer's profile page
	 */
	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public String addProject(@Valid @ModelAttribute("newProject") Project project, BindingResult result,
			Principal principal) {

		String username = principal.getName();
		project.setEmployer((Employer) userService.findOneByUsername(username));
		if (result.hasErrors()) {
			return "employerProfile";
		}
		projectService.saveProject(project);
		return "redirect:/employer/profile.html";

	}

	/**
	 * Binds the value coming from Spring Form for category to it's object from
	 * database. Uses CategoryEditor inner class for this.
	 * 
	 * @param binder
	 *            Object for binding data from web request parameter.
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	}

	/**
	 * @author KESHAV Inner class to convert the value from spring form to
	 *         actual object from database.
	 *
	 */
	public class CategoryEditor extends PropertyEditorSupport {

		private final CategoryService categoryService;

		public CategoryEditor(CategoryService categoryService) {
			this.categoryService = categoryService;
		}

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			try {
				Category category = categoryService.getCategoryById(Integer.parseInt(text));
				setValue(category);
			} catch (NumberFormatException e) {
				setValue(null);
			}

		}
	}

	/**
	 * Binds the list of ids coming from spring form as skills of project to
	 * actual list of skills objects.
	 * 
	 * @param binder
	 *            Object for binding data from web request parameter.
	 */
	@InitBinder
	public void skillsBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(List.class, "skills", new CustomCollectionEditor(List.class) {

			protected Object convertElement(Object element) {
				if (element != null) {
					Skills skill = new Skills();
					try {
						Integer id = Integer.parseInt(element.toString());
						skill = skillService.getSkillById(id);
					} catch (NumberFormatException e) {
						skill = skillService.getSkillBySkillTitle(SkillTitle.valueOf(element.toString()));
					}
					return skill;
				}
				return null;
			}
		});
	}

}
