package com.freshersProject.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.service.RestaurantDetailsService;

@RestController
public class RestaurantDetailsController {
	@Autowired
	private RestaurantDetailsService restaurantDetailsService;

	@PostMapping(value = "/addTableDetails")
	public String addTableDetails(@RequestBody RestaurantDetails restaurantDetails) {
		return restaurantDetailsService.addTableDetails(restaurantDetails);
		

	}

	@GetMapping(value = "/allTablesData")
	public List<RestaurantDetails> getAlldetail() {
		List<RestaurantDetails> list = restaurantDetailsService.getAlldetail();

		return list;
	}

	@PutMapping("/updateTableDetails")
	public String updateTableDetail(@RequestBody RestaurantDetails restaurantDetails) {

		return restaurantDetailsService.updateTableDetail(restaurantDetails);
	}
}
