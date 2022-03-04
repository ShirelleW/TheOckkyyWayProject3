package com.winecoff.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winecoff.backend.models.Toiletries;

public interface ToiletriesRepository extends JpaRepository<Toiletries, Integer> {

	List<Toiletries> findByCategory(String category); 
}
