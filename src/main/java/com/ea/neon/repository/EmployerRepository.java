package com.ea.neon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

	Employer findOneByCredentialsUserName(String username);

	Employer findOneByFirstName(String firstName);

}
