
package com.ea.neon.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ea.neon.domain.Freelancer;
import com.ea.neon.service.ProjectService;
import com.ea.neon.service.UserService;

/**
 * @author sabeen
 */

@Controller
@RequestMapping(value = "/freelancer")
public class FreelancerController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	/**
	 * Method to get current user's information and bind it to freelancer's
	 * profile page using model. Uses principal object to get username of
	 * current user(freelancer)
	 * @param model object for sending attributes to jsp
	 * @param principal object to get username of current user 
	 * @return jsp page for freelancer's profile
	 */
	@RequestMapping(value = "/profile")
	public String viewProfile(Model model, Principal principal) {

		Freelancer freelancer = userService.findFreelancerByUserName(principal.getName());
		model.addAttribute("listProject", projectService.findAllAppliedProjects(freelancer.getId()));
		model.addAttribute("currentUser", freelancer);
		return "freelancer_profile";
	}

}
