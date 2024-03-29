package com.viktor.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viktor.backend.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	
}