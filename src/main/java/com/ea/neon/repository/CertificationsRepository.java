package com.ea.neon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ea.neon.domain.Certifications;

public interface CertificationsRepository extends JpaRepository<Certifications, Integer> {
	
	@Query(nativeQuery=true,value="select * from Certifications e, Freelancer f where e.certifications_id = f.id and f.id=?1")
	List<Certifications> findByFreelancer(Integer id);
}
