package com.ea.neon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
