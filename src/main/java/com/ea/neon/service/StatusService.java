package com.ea.neon.service;

import com.ea.neon.domain.Status;
import com.ea.neon.domain.Status.ProjectStatus;

public interface StatusService {

	public Status getStatusByProjectStatus(ProjectStatus projectStatus);
	
}
