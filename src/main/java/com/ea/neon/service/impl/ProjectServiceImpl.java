package com.ea.neon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.neon.domain.Category.CategoryTitle;
import com.ea.neon.domain.Employer;
import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;
import com.ea.neon.domain.Skills.SkillTitle;
import com.ea.neon.domain.Status.ProjectStatus;
import com.ea.neon.repository.FreelancerRepository;
import com.ea.neon.repository.ProjectRepository;
import com.ea.neon.repository.StatusRepository;
import com.ea.neon.service.ProjectService;
import com.ea.neon.validation.aspect.ServiceValidation;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private FreelancerRepository freelancerRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Override
	@ServiceValidation
	public void saveProject(Project project) {
		project.setStatus(statusRepository.findOneByProjectStatus(ProjectStatus.PENDING));

		projectRepository.save(project);
	}

	@Override
	public List<Project> findAllByEmployer(Employer employer) {
		List<Project> projects = projectRepository.findAllByEmployer(employer);

		for (Project project : projects) {
			project.setFreelancers(freelancerRepository.findAllByProjectsId(project.getId()));
			for (Freelancer f : project.getFreelancers()) {
				f.setProjects(projectRepository.findAllByFreelancers(Arrays.asList(f)));
			}
		}

		return projects;
	}

	@Override
	public List<Project> findAll(Integer freelancerId) {
		List<Project> projects = new ArrayList<>();
		List<Integer> projectIds = projectRepository.findAllNotAppliedProjects(freelancerId);
		if(projectIds==null || projectIds.isEmpty() ){
			System.out.println("null return --> findall");
			return projects;
		}
		return projectRepository.findAllByProjectId(projectIds);
	}

	@Override
	public Project findById(Integer id) {
		// TODO Auto-generated method stub
		return projectRepository.findOne(id);
	}

	@Override
	public List<Project> findAllAppliedProjects(Integer freelancerId) {
		List<Integer> projectId = projectRepository.findAllProjectIdByFreelancer(freelancerId);
		List<Project> projects = new ArrayList<>();
		if(projectId==null || projectId.isEmpty() ){

			return projects;
		}
		
		for (Integer pId : projectId) {
			
			projects.add(projectRepository.findById(pId));
			
		}
		return projects;
	}

	@Override
	public List<Project> findByTitleAndDesc(String search) {
		// TODO Auto-generated method stub
		return projectRepository.findByDescAndTitle(search);
	}

	@Override
	public List<Project> findAllNotAppliedprojects(String key, Integer freelancerId) {
		
		List<Integer> ids = projectRepository.findAllNotAppliedProjects(freelancerId);
		List<Project> projects = new ArrayList<>();
		if(ids==null || ids.isEmpty() ){
			System.out.println("null return --> findall");
			return projects;
		}
		return projectRepository.findByDescAndTitleByNotApplied(ids, key);
	}

	@Override
	public List<Project> findAllByFilter(Integer freelancerId, List<SkillTitle> skillTitles,
			CategoryTitle categoryTitle, Double minBudget, Double maxBudget) {
		List<Integer> ids = projectRepository.findAllNotAppliedProjects(freelancerId);
		List<Project> projects = new ArrayList<>();
		if(ids==null || ids.isEmpty() ){
			System.out.println("null return --> findall");
			return projects;
		}
		return projectRepository.findAllByFilter(ids, skillTitles, categoryTitle, minBudget, maxBudget);
	}

	@Override
	public Project getProjectById(Integer projectId) {
		Project project = projectRepository.findOne(projectId);
		project.setFreelancers(freelancerRepository.findAllByProjectsId(projectId));
		return project;
	}

	@Override
	public void updateProject(Project project) {
		projectRepository.save(project);
	}

}
