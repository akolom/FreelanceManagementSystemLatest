package com.ea.neon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.neon.domain.Authority;
import com.ea.neon.repository.AuthorityRepository;
import com.ea.neon.service.AuthorityService;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public void save(Authority authority) {
		authorityRepository.save(authority);

	}

	@Override
	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	@Override
	public Authority findOneById(Integer id) {
		return authorityRepository.findOne(id);
	}

	@Override
	public Authority findOneByName(String name) {
		return authorityRepository.findOneByName(name);
	}

}
