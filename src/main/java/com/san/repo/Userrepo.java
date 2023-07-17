package com.san.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.san.model.User;

@Repository
public interface Userrepo extends JpaRepository<User, Long> {

	
	
	
	
	Optional<User> findByUsername(String username);

	
	
}
