package com.ea.neon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.neon.domain.Status;
import com.ea.neon.domain.Status.ProjectStatus;
import com.ea.neon.repository.StatusRepository;
import com.ea.neon.service.StatusService;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	@Override
	public Status getStatusByProjectStatus(ProjectStatus projectStatus) {
		return statusRepository.findOneByProjectStatus(projectStatus);
	}

}
