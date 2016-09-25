package com.ea.neon.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.ea.neon.dto.ProjectApplyDTO;
import com.ea.neon.service.UserService;

/**
 * @author Sabeen
 * */
public class ApplyListener implements MessageListener{
@Autowired
UserService userService;


	/* *
	 * method to listen from broker and receive message(ProjectApplyDTO Object) send from sender 
	 * and save the freelancer to project (Freelancer Applying for project).
	 **/
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message; 
		ProjectApplyDTO projectApplyDTO = null;
		try {
			projectApplyDTO = (ProjectApplyDTO) objectMessage.getObject();
			System.out.println("JMS LIstener output############");
			
			System.out.println(projectApplyDTO.getProject().getName());
			userService.saveFreelancerInProject(projectApplyDTO.getProject(), projectApplyDTO.getFreelancer());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	

}
