package com.ea.neon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ea.neon.domain.Category.CategoryTitle;
import com.ea.neon.domain.Employer;
import com.ea.neon.domain.Freelancer;
import com.ea.neon.domain.Project;
import com.ea.neon.domain.Skills.SkillTitle;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	List<Project> findAllByEmployer(Employer employer);

	List<Project> findAllByFreelancers(List<Freelancer> freelancers);

	@Query("select p from Project p inner join p.category.skills s where s.skillTitle in ?1")
	List<Project> findBySkillTitles(List<SkillTitle> skillTitles);

	@Query("select p from Project p where p.name like %?1% or  p.description like %?1%")
	List<Project> findByDescAndTitle(String search);

	@Query(nativeQuery = true, value = "select fp.projects_id from Freelancer_Project fp where fp.freelancers_id = ?1")
	List<Integer> findAllProjectIdByFreelancer(Integer freelancerId);

	@Query(nativeQuery = true, value = " select p.id from Project p where p.id not in (select projects_id from Freelancer_Project where freelancers_id = ?1)")
	List<Integer> findAllNotAppliedProjects(Integer freelancerId);

	@Query("select p from Project p where (p.id in ?1) and (p.name like %?2% or  p.description like %?2%)")
	List<Project> findByDescAndTitleByNotApplied(List<Integer> projectId, String search);

	Project findById(Integer id);

	@Query("select p from Project p where " + "p.id in " + "(select p3.id from Project p3 where p3.id in ?1) "
			+ "and p.id in" + "(select p1.id from Project p1 inner join p1.category.skills s where s.skillTitle in ?2 )"
			+ "and p.id in "
			+ "(select p2.id from Project p2 where p2.category.categoryTitle = ?3  and p2.budget between ?4 and ?5)")
	List<Project> findAllByFilter(List<Integer> ids, List<SkillTitle> skillTitles, CategoryTitle categoryTitle,
			Double minBudget, Double maxBudget);

	@Query("select p from Project p where p.id in ?1")
	List<Project> findAllByProjectId(List<Integer> projectIds);

	@Query(nativeQuery = true, value = "select * from Project where id in ( select projects_id from Freelancer_Project where "
			+ "freelancers_id=?1)")
	List<Project> findByFreelancer(Integer id);
	
	
	
}
