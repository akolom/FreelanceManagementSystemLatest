package com.ea.neon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KESHAV
 * 
 *         Controller to redirect to homepage.
 *
 */
@Controller
public class HomeController {

	/**
	 * Method to send homepage jsp name when requested.
	 * 
	 * @return name of jsp page of homepage.
	 */
	@RequestMapping({ "/", "/index" })
	public String home() {
		return "welcome";
	}

}
