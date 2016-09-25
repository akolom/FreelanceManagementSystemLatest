package com.ea.neon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.Freelancer;

public interface FreelancerRepository extends JpaRepository<Freelancer, Integer> {

	List<Freelancer> findAllByProjectsId(Integer id);
	
}
