package com.viktor.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viktor.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}