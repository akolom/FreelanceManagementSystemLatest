package com.ea.neon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	Authority findOneByName(String name);

}
