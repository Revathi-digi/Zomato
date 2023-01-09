package com.freshersProject.ecommerce.service;

import java.util.List;

import com.freshersProject.ecommerce.model.RestaurantDetails;

public interface RestaurantDetailsService {

	String addTableDetails(RestaurantDetails restaurantDetails);

	List<RestaurantDetails> getAlldetail();

	String updateTableDetail(RestaurantDetails restaurantDetails);

}
