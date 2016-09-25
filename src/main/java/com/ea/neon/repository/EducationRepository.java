package com.ea.neon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ea.neon.domain.Education;

public interface EducationRepository extends JpaRepository<Education, Integer> {

	@Query(nativeQuery=true,value="select * from Education e, Freelancer f where e.freelancer_education = f.id and f.id=?1")
	List<Education> findByFreelancer(Integer id);
}
