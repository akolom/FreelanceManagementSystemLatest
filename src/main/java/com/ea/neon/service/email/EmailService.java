/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.ea.neon.service.email;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;

/**
 * @author KESHAV
 * 
 *         Service class to send an email.
 *
 */
@Service("emailService")
public class EmailService {

	private static final String IM_THE_GUY = "templates/images/imtheguy.jpg";

	private static final String JPG_MIME = "image/jpg";

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	/**
	 * This method sends an interview invitation email to freelancer.
	 * 
	 * @param freelancer
	 *            Freelancer who is invited for interview.
	 * @param project
	 *            Project for which freelancer is being invited for interview.
	 * @param locale
	 *            Locale represents a specific geographical, political, or
	 *            cultural region.
	 * @throws MessagingException
	 *             Throws Messaging exception if the email fails to send to
	 *             freelancer email address.
	 */
	public void sendInterviewInvitation(final Freelancer freelancer, final Project project, final Locale locale)
			throws MessagingException {

		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", freelancer.getFirstName() + " " + freelancer.getLastName());
		thymeContext.setVariable("employer", project.getEmployer());
		thymeContext.setVariable("project", project);
		thymeContext.setVariable("profileLink",
				"http://localhost:8080/FreelanceManagementSystem/employer/profile.html?id="
						+ project.getEmployer().getId());

		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		messageHelper.setSubject("Email for an Interview");

		messageHelper.setTo(freelancer.getEmail());

		final String htmlContent = this.templateEngine.process("sendInterviewEmail", thymeContext);
		messageHelper.setText(htmlContent, true);

		messageHelper.addInline("imtheguy", new ClassPathResource(IM_THE_GUY), JPG_MIME);

		this.mailSender.send(mimeMessage);
	}

	/**
	 * This method returns hiring email to freelancer.
	 * 
	 * @param freelancer
	 *            Freelancer who is hired for the project.
	 * @param project
	 *            Project for which freelancer is being invited for interview.
	 * @param locale
	 *            Locale represents a specific geographical, political, or
	 *            cultural region.
	 * @throws MessagingException
	 *             Throws Messaging exception if the email fails to send to
	 *             freelancer email address.
	 */
	public void sendHiringEmail(Freelancer freelancer, Project project, Locale locale) throws MessagingException {
		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", freelancer.getFirstName() + " " + freelancer.getLastName());
		thymeContext.setVariable("employer", project.getEmployer());
		thymeContext.setVariable("project", project);
		thymeContext.setVariable("profileLink",
				"http://localhost:8080/FreelanceManagementSystem/employer/profile.html?id="
						+ project.getEmployer().getId());

		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		messageHelper.setSubject("You are Hired!!");

		messageHelper.setTo(freelancer.getEmail());

		final String htmlContent = this.templateEngine.process("sendHiringEmail", thymeContext);
		messageHelper.setText(htmlContent, true);

		messageHelper.addInline("imtheguy", new ClassPathResource(IM_THE_GUY), JPG_MIME);

		this.mailSender.send(mimeMessage);
	}

}
