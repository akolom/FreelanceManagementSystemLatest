package com.ea.neon.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ea.neon.domain.Category;
import com.ea.neon.domain.Category.CategoryTitle;
import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;
import com.ea.neon.domain.Skills;
import com.ea.neon.domain.Skills.SkillTitle;
import com.ea.neon.domain.Status;
import com.ea.neon.domain.Status.ProjectStatus;
import com.ea.neon.dto.ProjectApplyDTO;
import com.ea.neon.dto.ProjectSearchDTO;
import com.ea.neon.repository.EmployerRepository;
import com.ea.neon.repository.ProjectRepository;
import com.ea.neon.sender.MessageSender;
import com.ea.neon.service.CategoryService;
import com.ea.neon.service.ProjectService;
import com.ea.neon.service.SkillService;
import com.ea.neon.service.StatusService;
import com.ea.neon.service.UserService;

/**
 * @author KESHAV and SABEEN
 * 
 *         Controller to handle all project requests.
 *
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	MessageSender messageSender;

	@Autowired
	ProjectService projectService;
	@Autowired
	CategoryService categoryService;

	@Autowired
	SkillService skillService;

	@Autowired
	UserService userService;
	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployerRepository employerRepository;

	@ModelAttribute("userStory")
	public Project getProject() {
		return new Project();
	}

	@Autowired
	private StatusService statusService;

	/**
	 * @author KESHAV
	 * 
	 *         This method updates the project and freelancers and set the
	 *         project status as accepted project.
	 * 
	 * @param freelancerId
	 *            Id of freelancer who is being hired.
	 * @param projectId
	 *            Id of project for which freelancer is being hired.
	 * @return redirect link to send an email to the freelancer about hiring.
	 */
	@RequestMapping("/hireFreelancer")
	public String hireFreelancer(@RequestParam("f_id") Integer freelancerId, @RequestParam("p_id") Integer projectId) {

		Project project = projectService.getProjectById(projectId);
		Freelancer freelancer = (Freelancer) userService.findUserById(freelancerId);

		Status status = statusService.getStatusByProjectStatus(ProjectStatus.ACCEPTED);

		project.setStatus(status);

		for (Freelancer f : project.getFreelancers()) {
			if (f.getId() != freelancerId) {
				userService.removeProjectFromFreelancer(f, project);
			}
		}

		project.getFreelancers().clear();

		project.getFreelancers().add(freelancer);

		projectService.updateProject(project);

		return "redirect:/email/forHiring.html?f_id=" + freelancerId + "&&p_id=" + projectId;
	}

	/**
	 * method to get get all the list of projects which is not applied by
	 * current user and bind to freelancer's project page. Uses principal object
	 * to get username of current user(freelancer)
	 * 
	 * @param model
	 *            object for sending attributes to jsp
	 * @param principal
	 *            object to get username of current user
	 * @return name of jsp page showing projects
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String listProjects(Model model, Principal principal) {
		Freelancer freelancer = userService.findFreelancerByUserName(principal.getName());
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("skill", skillService.findAll());
		model.addAttribute("listProject", projectService.findAll(freelancer.getId()));
		return "project_read";
	}

	/**
	 * method to get all the applied project list by Freelancer, also showing
	 * status pending, declined accepted, calledForInterview of particular
	 * project. Uses principal object to get username of current
	 * user(freelancer)
	 * 
	 * @param model
	 *            object for sending attributes to jsp
	 * @param principal
	 *            object to get username of current user
	 * @return jsp page showing all applied projects
	 */
	@RequestMapping(value = "/freelancer_project", method = RequestMethod.GET)
	public String freelancerProject(Model model, Principal principal) {
		Freelancer freelancer = userService.findFreelancerByUserName(principal.getName());
		model.addAttribute("listProject", projectService.findAllAppliedProjects(freelancer.getId()));
		return "freelancer_project";
	}

	@ModelAttribute("projectSearch")
	public ProjectSearchDTO projectSearchDetails() {
		return new ProjectSearchDTO();
	}

	/**
	 * method which is called when freelancer applies for a project. uses
	 * ProjectAppyDTO to set freelancer and project. uses MessageSender
	 * Interface to send message(Object) to queue of Broker.Uses principal
	 * object to get username of current user(freelancer).
	 * 
	 * @param id
	 *            selected project's id from jsp
	 * @param principal
	 *            object to get username of current user
	 * @return redirection to freelancer_project jsp
	 */
	@RequestMapping(value = "/applyProject", method = RequestMethod.GET)
	public String applyProject(@RequestParam Integer id, Principal principal) {

		Project project = projectService.findById(id);
		Freelancer freelancer = userService.findFreelancerByUserName(principal.getName());
		ProjectApplyDTO projectApplyDTO = new ProjectApplyDTO();
		projectApplyDTO.setFreelancer(freelancer);
		projectApplyDTO.setProject(project);
		messageSender.sendMessage(projectApplyDTO);

		return "redirect:freelancer_project.html";
	}

	/**
	 * 
	 * method to search project based on user search string. It takes the string
	 * as a request paramter and calls Project Service to find all projects
	 * which is not applied by freelancer and matches the string. Uses principal
	 * object to get username of current user(freelancer).
	 * 
	 * @param model
	 *            object for sending attributes to jsp
	 * @param principal
	 *            object to get username of current user
	 * @param search
	 *            typed string from jsp
	 * @return jsp page for showing all projects
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchProjects(Model model, @RequestParam String search, Principal principal) {
		Freelancer freelancer = userService.findFreelancerByUserName(principal.getName());

		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("skill", skillService.findAll());
		model.addAttribute("listProject", projectService.findAllNotAppliedprojects(search, freelancer.getId()));
		return "project_read";
	}

	/**
	 * method which binds ProjectSeachDTO object with spring form, uses to model
	 * to send attributes to view. Gets user selected filter and then calls
	 * ProjectService to list all the project that matches the filter.Uses
	 * principal object to get username of current user(freelancer).
	 * 
	 * @param model
	 *            object for sending attributes to jsp
	 * @param principal
	 *            object to get username of current user
	 * @param projectSearchDTO
	 *            Model Attribute
	 * @return jsp page for all projects
	 */
	@RequestMapping(value = "/filterSearch", method = RequestMethod.POST)
	public String filterSearch(Model model, @ModelAttribute("projectSearch") ProjectSearchDTO projectSearchDTO,
			Principal principal) {
		Freelancer freelancer = userService.findFreelancerByUserName(principal.getName());

		CategoryTitle categoryTitle = projectSearchDTO.getCategory().getCategoryTitle();
		List<SkillTitle> skills = new ArrayList<>();

		for (Skills s : projectSearchDTO.getSkills()) {
			skills.add(s.getSkillTitle());
		}
		Double maxBudget = projectSearchDTO.getMaxBudget();

		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("skill", skillService.findAll());

		model.addAttribute("listProject",
				projectService.findAllByFilter(freelancer.getId(), skills, categoryTitle, 1.0, maxBudget));
		return "project_read";
	}

	/**
	 * Binds the value coming from Spring Form for category to it's object from
	 * database. Uses CategoryEditor inner class for this.
	 **/
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	}

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
	 * @param binder
	 *            Binds the value coming from Spring Form for skills to it's
	 *            object from database.
	 */
	@InitBinder
	public void skillsBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(List.class, "skills", new CustomCollectionEditor(List.class) {

			protected Object convertElement(Object element) {
				if (element != null) {
					Integer id = Integer.parseInt(element.toString());
					Skills skill = skillService.getSkillById(id);
					return skill;
				}
				return null;
			}
		});
	}

}
