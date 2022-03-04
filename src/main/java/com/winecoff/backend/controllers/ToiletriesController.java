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
import com.winecoff.backend.models.Toiletries;
import com.winecoff.backend.repositories.FoodRepository;
import com.winecoff.backend.repositories.ToiletriesRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class ToiletriesController {
	
	
	@Autowired
	private ToiletriesRepository toiletriesRepo; 
	
	
	// Read and return all toiletry items
	@GetMapping("alltoiletries")
	public List<Toiletries> getAllToiletries(){
		return toiletriesRepo.findAll(); 
	}
	
	//Read and return all by category
	@GetMapping("toiletries/category/{category}")
	public List<Toiletries> findByCategory(@PathVariable String category){
		return (List<Toiletries>) toiletriesRepo.findByCategory(category); 
	}


//	Get item by id
	@GetMapping("toiletries/{id}")
	public ResponseEntity<Toiletries> getToiletriesById(@PathVariable int id){
		Toiletries toiletries = toiletriesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Toiletry not found.")); 
		return ResponseEntity.ok(toiletries); 
	}
	
	
//	 Delete by id 
//	Question: Wont this change the whole menu and not whats just in the cart? 
	@DeleteMapping("toiletries/{id}")
	public ResponseEntity<String> deleteToiletry(@PathVariable int id) {
			toiletriesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Toiletry not found."));
			
//			Delete method from jpa
			toiletriesRepo.deleteById(id); 
			return new ResponseEntity<>("Toiletry has been deleted", HttpStatus.OK); 
		}
	
//	add toiletry to api
	@PostMapping("addtoiletry")
	public Toiletries newToiletry(@RequestBody Toiletries toiletry) {
		return toiletriesRepo.save(toiletry); 
	}
	
	
//	update food obj keys, such as name to the api
	@PutMapping("toiletries/{id}")
	public ResponseEntity<Toiletries> updateToiletry(@PathVariable int id, @RequestBody Toiletries newToiletriesInfo){
		Toiletries foundToiletry = toiletriesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Toiletry not found."));
		
		foundToiletry.setCategory(newToiletriesInfo.getCategory()); 
		foundToiletry.setName(newToiletriesInfo.getName()); 
		foundToiletry.setPrice(newToiletriesInfo.getPrice()); 

		
		Toiletries updatedToiletries = toiletriesRepo.save(foundToiletry);
		
		return ResponseEntity.ok(updatedToiletries); 
	}
}
