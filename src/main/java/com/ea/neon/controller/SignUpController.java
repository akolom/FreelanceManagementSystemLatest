package com.ea.neon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ea.neon.domain.Authority;
import com.ea.neon.domain.User;
import com.ea.neon.service.AuthorityService;
import com.ea.neon.service.UserService;

/**
 * @author AKOLOM and WALLELGN Controller to handle signup system.
 */
@Controller
public class SignUpController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

	/**
	 * This method will send sugn up form.
	 * 
	 * @param model
	 *            Object on which data from database is set as attributes.
	 * @return name of jsp page having sign up form.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String sinup(Model model) {
		model.addAttribute("authority", authorityService.findAll());
		return "signup";
	}

	/**
	 * This method validates user object and saves in database. Validation error
	 * will be stored in binding result. If there is error in binding result,
	 * signup page will be displayed with errors.
	 * 
	 * @param user
	 *            Object bound with spring form for signing up.
	 * @param result
	 *            Object which will store errors while validating.
	 * @return redirect to homepage after signup, so that user can log in using
	 *         username and password.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if (result.hasErrors()) {
			return "signup";
		}
		Authority authority = authorityService.findOneByName(user.getCredentials().getAuthority().getName());
		user.getCredentials().setAuthority(authority);
		userService.save(user);
		return "redirect:/";
	}

}
