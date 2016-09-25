package com.ea.neon.service;

import java.util.List;

import com.ea.neon.domain.Employer;
import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;
import com.ea.neon.domain.User;

public interface UserService {

	public void save(User user);

	public List<User> findAll();

	public User findByEmail(String email);

	public User update(User user);

	public User findOneByUsername(String userName);

	public User findUserById(Integer id);

	public Employer findEmployerById(Integer id);

	public void removeProjectFromFreelancer(Freelancer f, Project project);

	public Freelancer findFreelancerById(Integer id);

	public void saveFreelancerInProject(Project project, Freelancer freelancer);

	Freelancer findFreelancerByUserName(String username);

}
