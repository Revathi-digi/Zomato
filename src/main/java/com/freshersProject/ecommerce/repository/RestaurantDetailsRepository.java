package com.freshersProject.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshersProject.ecommerce.model.RestaurantDetails;

@Repository
public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Integer> {

	RestaurantDetails findByrestaurantId(int restaurantId);

	RestaurantDetails findByRestaurantName(String restaurantName);

	RestaurantDetails findById(int restaurantId);

}
