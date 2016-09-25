package com.ea.neon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.neon.domain.Employer;
import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;
import com.ea.neon.domain.User;
import com.ea.neon.repository.AddressRepository;
import com.ea.neon.repository.CertificationsRepository;
import com.ea.neon.repository.EducationRepository;
import com.ea.neon.repository.EmployerRepository;
import com.ea.neon.repository.ExperienceRepository;
import com.ea.neon.repository.FreelancerRepository;
import com.ea.neon.repository.ProjectRepository;
import com.ea.neon.repository.SkillsRepository;
import com.ea.neon.repository.UserRepository;
import com.ea.neon.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private EducationRepository educationRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private FreelancerRepository freelancerRepository;

	@Autowired
	private CertificationsRepository certificationsRepository;

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ExperienceRepository experienceRepository;

	public void save(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		user.getCredentials().setEnabled(true);
		user.getCredentials().setPassword(encoder.encode(user.getCredentials().getPassword()));
		String authorityName = user.getCredentials().getAuthority().getName();
		switch (authorityName) {
		case "Employer":
			User employer = new Employer();
			employer.setFirstName(user.getFirstName());
			employer.setLastName(user.getLastName());
			employer.setEmail(user.getEmail());
			employer.setCredentials(user.getCredentials());
			userRepository.save(employer);
			break;
		case "Freelancer":
			User freelancer = new Freelancer();
			freelancer.setFirstName(user.getFirstName());
			freelancer.setLastName(user.getLastName());
			freelancer.setEmail(user.getEmail());
			freelancer.setCredentials(user.getCredentials());
			userRepository.save(freelancer);
			break;
		default:
			userRepository.save(user);
			break;
		}
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	public User update(User user) {
		try {
			return userRepository.save(user);
		} catch (StaleObjectStateException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public User findOneByUsername(String userName) {
		return userRepository.findOneByCredentialsUserName(userName);
	}

	public void saveFreelancerInProject(Project project, Freelancer freelancer) {
		List<Project> projects = freelancer.getProjects();
		if (projects == null) {
			projects = new ArrayList<>();
		}
		projects.add(project);
		freelancer.setProjects(projects);
		userRepository.save(freelancer);
	}

	@Override
	public User findUserById(Integer id) {
		User user = userRepository.findOne(id);
		user.getAddresses();
		user.getProfile();
		user.getCredentials();
		return user;
	}

	@Override
	public Employer findEmployerById(Integer id) {
		Employer employer = employerRepository.findOne(id);
		employer.setAddresses(addressRepository.findAllByUser(employer));
		employer.getCredentials();
		employer.getProfile();
		return employer;
	}

	@Override
	public Freelancer findFreelancerById(Integer id) {

		Freelancer freelancer = freelancerRepository.findOne(id);
		freelancer.setAddresses(addressRepository.findAllByUser(freelancer));
		freelancer.getCredentials();
		freelancer.getProfile();
		freelancer.setEducations(educationRepository.findByFreelancer(freelancer.getId()));
		freelancer.setCertifications(certificationsRepository.findByFreelancer(freelancer.getId()));
		freelancer.setProjects(projectRepository.findByFreelancer(freelancer.getId()));
		freelancer.setSkills(skillsRepository.findByFreelancer(freelancer.getId()));
		freelancer.setExperiances(experienceRepository.findByFreelancer(freelancer.getId()));

		return freelancer;
	}

	@Override
	public void removeProjectFromFreelancer(Freelancer freelancer, Project project) {
		List<Project> projects = projectRepository.findAllByFreelancers(Arrays.asList(freelancer));
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getId() == project.getId()) {
				projects.remove(i);
			}
		}
		freelancer.setProjects(projects);
		freelancerRepository.save(freelancer);
	}

	@Override
	public Freelancer findFreelancerByUserName(String username) {
		Freelancer freelancer = (Freelancer) userRepository.findOneByCredentialsUserName(username);
		freelancer.setAddresses(addressRepository.findAllByUser(freelancer));
		freelancer.getCredentials();
		freelancer.getProfile();
		freelancer.setEducations(educationRepository.findByFreelancer(freelancer.getId()));
		freelancer.setCertifications(certificationsRepository.findByFreelancer(freelancer.getId()));
		freelancer.setProjects(projectRepository.findByFreelancer(freelancer.getId()));
		freelancer.setSkills(skillsRepository.findByFreelancer(freelancer.getId()));
		freelancer.setExperiances(experienceRepository.findByFreelancer(freelancer.getId()));

		return freelancer;
	}

}
