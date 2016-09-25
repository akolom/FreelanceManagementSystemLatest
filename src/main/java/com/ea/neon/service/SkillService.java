package com.ea.neon.service;

import java.util.List;

import com.ea.neon.domain.Skills;
import com.ea.neon.domain.Skills.SkillTitle;

public interface SkillService {

	public List<Skills> findAll();

	public Skills getSkillById(Integer id);

	public Skills getSkillBySkillTitle(SkillTitle valueOf);
	
}
