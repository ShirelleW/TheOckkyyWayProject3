package com.winecoff.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winecoff.backend.models.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
	
	List<Food> findByName(String name); 
	List<Food> findByCategory(String category);
	
}
