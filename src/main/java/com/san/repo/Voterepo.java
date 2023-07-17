package com.san.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.san.model.User;
import com.san.model.Vote;

@Repository
public interface Voterepo extends JpaRepository<Vote, Long> {
	
	boolean existsByUser(User user);

}
