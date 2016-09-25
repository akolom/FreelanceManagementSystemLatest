package com.ea.neon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.neon.domain.Address;
import com.ea.neon.domain.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findAllByUser(User user);

}
