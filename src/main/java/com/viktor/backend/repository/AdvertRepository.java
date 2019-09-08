package com.viktor.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viktor.backend.model.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
	
}