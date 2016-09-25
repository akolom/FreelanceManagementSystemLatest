package com.ea.neon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.neon.domain.Skills;
import com.ea.neon.domain.Skills.SkillTitle;
import com.ea.neon.repository.SkillsRepository;
import com.ea.neon.service.SkillService;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillsRepository skillsRepository;

	@Override
	public List<Skills> findAll() {
		return skillsRepository.findAll();
	}

	@Override
	public Skills getSkillById(Integer id) {
		return skillsRepository.findOne(id);
	}

	@Override
	public Skills getSkillBySkillTitle(SkillTitle skillTitle) {
		return skillsRepository.findOneBySkillTitle(skillTitle);
	}

}
