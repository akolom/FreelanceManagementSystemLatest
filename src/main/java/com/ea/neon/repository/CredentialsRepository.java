package com.ea.neon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, Integer> {
	Credentials findOneByUserName(String userName);

}
