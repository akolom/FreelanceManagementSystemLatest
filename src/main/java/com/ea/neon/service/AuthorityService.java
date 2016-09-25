package com.ea.neon.service;

import java.util.List;

import com.ea.neon.domain.Authority;

public interface AuthorityService {
	
	public void save(Authority authority);

	public List<Authority> findAll();

	public Authority findOneById(Integer id);

	public Authority findOneByName(String name);

}
