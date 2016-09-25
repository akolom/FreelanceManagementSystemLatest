package com.ea.neon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findOneByEmail(String email);

	User findOneByCredentialsUserName(String userName);

}
