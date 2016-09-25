package com.ea.neon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.Status;
import com.ea.neon.domain.Status.ProjectStatus;

public interface StatusRepository extends JpaRepository<Status, Integer> {

	Status findOneByProjectStatus(ProjectStatus projectStatus);
	
}
