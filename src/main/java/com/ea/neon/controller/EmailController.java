package com.ea.neon.controller;

import java.util.Locale;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;
import com.ea.neon.domain.Status;
import com.ea.neon.domain.Status.ProjectStatus;
import com.ea.neon.domain.User;
import com.ea.neon.service.ProjectService;
import com.ea.neon.service.StatusService;
import com.ea.neon.service.UserService;
import com.ea.neon.service.email.EmailService;

/**
 * @author KESHAV
 * 
 *         Controller class for email sending Sends email when employer invites
 *         freelancer for interview or hire the freelancer
 */
@Controller
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@Autowired
	private StatusService statusService;

	/**
	 * Handles sending email for an interview to freelancer.
	 * 
	 * @param freelancerId
	 *            Id of freelancer who is being invited for interview.
	 * @param projectId
	 *            Id of project for which freelancer is being invited for
	 *            interview.
	 * @param model
	 *            Object on which data from database is set as attributes.
	 * @return redirect link to employer's profile
	 */
	@RequestMapping("/forInterview")
	public String sendInterviewEmail(@RequestParam("f_id") Integer freelancerId,
			@RequestParam("p_id") Integer projectId, Model model) {
		try {
			Project project = projectService.getProjectById(projectId);
			User freelancer = userService.findUserById(freelancerId);
			emailService.sendInterviewInvitation((Freelancer) freelancer, project, new Locale("en"));

			Status status = statusService.getStatusByProjectStatus(ProjectStatus.CALL_FOR_INTERVIEW);
			project.setStatus(status);

			projectService.updateProject(project);

			model.addAttribute("error", false);
			model.addAttribute("f", freelancer);
		} catch (MessagingException e) {
			model.addAttribute("error", true);
		}
		return "redirect:/employer/profile.html";
	}

	/**
	 * Handles sending hiring email to freelancer.
	 * 
	 * @param freelancerId
	 *            Id of freelancer who is being hired.
	 * @param projectId
	 *            Id of project for which freelancer is being hired.
	 * @param model
	 *            Object on which data from database is set as attributes.
	 * @return redirect link to employer's profile
	 */
	@RequestMapping("/forHiring")
	public String sendHiringEmail(@RequestParam("f_id") Integer freelancerId, @RequestParam("p_id") Integer projectId,
			Model model) {
		try {
			Project project = projectService.getProjectById(projectId);
			User freelancer = userService.findUserById(freelancerId);
			emailService.sendHiringEmail((Freelancer) freelancer, project, new Locale("en"));

			Status status = statusService.getStatusByProjectStatus(ProjectStatus.ACCEPTED);
			project.setStatus(status);

			projectService.updateProject(project);

			model.addAttribute("error", false);
			model.addAttribute("f", freelancer);
		} catch (MessagingException e) {
			model.addAttribute("error", true);
		}
		return "redirect:/employer/profile.html";
	}

}
