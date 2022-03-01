package com.winecoff.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winecoff.backend.models.Food;
import com.winecoff.backend.repositories.FoodRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class FoodController {

	@Autowired
	private FoodRepository foodRepo; 
	
	@GetMapping("allfood")
	public List<Food> getAllFood(){
		return foodRepo.findAll(); 
	}
	
}
