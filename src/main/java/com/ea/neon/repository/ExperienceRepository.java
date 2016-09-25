package com.ea.neon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ea.neon.domain.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

	

	@Query(nativeQuery=true,value="select * from Experience e, Freelancer f where e.freelancer_experiances = f.id and f.id=?1")
	List<Experience> findByFreelancer(Integer id);
}
