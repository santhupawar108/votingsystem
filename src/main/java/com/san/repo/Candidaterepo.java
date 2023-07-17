package com.san.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.san.model.Candidate;

import jakarta.transaction.Transactional;

@Repository
public interface Candidaterepo  extends JpaRepository<Candidate, Long> {

	Optional<Candidate> findByName(String name);
  
	
	


}
