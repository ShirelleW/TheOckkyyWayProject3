package com.winecoff.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winecoff.backend.exceptions.ResourceNotFoundException;
import com.winecoff.backend.models.Food;
import com.winecoff.backend.repositories.FoodRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class FoodController {

	@Autowired
	private FoodRepository foodRepo; 
	
	
	// Read and return all food items
	@GetMapping("allfood")
	public List<Food> getAllFood(){
		return foodRepo.findAll(); 
	}
	
	//Read and return all by category
	@GetMapping("food/category/{category}")
	public List<Food> findByCategory(@PathVariable String category){
		return (List<Food>) foodRepo.findByCategory(category); 
	}


//	Get food item by id
	@GetMapping("food/{id}")
	public ResponseEntity<Food> getFoodById(@PathVariable int id){
		Food food = foodRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Food not found.")); 
		return ResponseEntity.ok(food); 
	}
	
	
//	 Delete by id 
//	Question: Wont this change the whole menu and not whats just in the cart? 
	@DeleteMapping("food/{id}")
	public ResponseEntity<String> deleteFood(@PathVariable int id) {
			foodRepo.findById(id);
//				.orElseThrow(() -> new ResourceNotFoundException("Student not found."));
			
//			Delete method from jpa
			foodRepo.deleteById(id); 
			return new ResponseEntity<>("Food has been deleted", HttpStatus.OK); 
		}
	
//	add food to api
	@PostMapping("addfood")
	public Food newFood(@RequestBody Food food) {
		return foodRepo.save(food); 
	}
	
	
//	update food obj keys, such as name to the api
	@PutMapping("food/{id}")
	public ResponseEntity<Food> updateFood(@PathVariable int id, @RequestBody Food newFoodInfo){
		Food foundFood = foodRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Food not found."));
		
		foundFood.setCategory(newFoodInfo.getCategory()); 
		foundFood.setName(newFoodInfo.getName()); 
		foundFood.setPrice(newFoodInfo.getPrice()); 
//		foundFood.setId(newFoodInfo.getId()); 
		
		Food updatedFood = foodRepo.save(foundFood);
		
		return ResponseEntity.ok(updatedFood);  
	}
	
}
