package com.freshersProject.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.repository.RestaurantDetailsRepository;
import com.freshersProject.ecommerce.service.RestaurantDetailsService;

@Service
public class RestaurantDetailsServiceImpl implements RestaurantDetailsService {
	@Autowired
	private RestaurantDetailsRepository restaurantDetailsRepository;

	@Override
	public String addTableDetails(RestaurantDetails restaurantDetails) {
		restaurantDetailsRepository.save(restaurantDetails);
		return "table details added successfully";
	}

	@Override
	public List<RestaurantDetails> getAlldetail() {
		List<RestaurantDetails> restaurantDetails = new ArrayList<>();
		restaurantDetailsRepository.findAll().forEach(tableDetail -> restaurantDetails.add(tableDetail));
		return restaurantDetails;
	}

	@Override
	public String updateTableDetail(RestaurantDetails restaurantDetails) {

		RestaurantDetails restaurantDetailsNew = restaurantDetailsRepository
				.findByRestaurantName(restaurantDetails.getRestaurantName());
		if (restaurantDetails.getRestaurantAddress().isBlank())
		{
			restaurantDetailsNew.setRestaurantAddress(restaurantDetails.getRestaurantAddress());
		}
		if (restaurantDetails.getRestaurantName().isBlank())
		{
			restaurantDetailsNew.setRestaurantName(restaurantDetails.getRestaurantName());
		}
		if (restaurantDetails.getLunchAvlTables() != 0)
		{
			restaurantDetailsNew.setLunchAvlTables(restaurantDetails.getLunchAvlTables());
		}
		if (restaurantDetails.getDinnerAvlTables() != 0)
		{
			restaurantDetailsNew.setDinnerAvlTables(restaurantDetails.getDinnerAvlTables());
		}
		if (restaurantDetails.getPerTableBookingPrice() != 0)
		{
			restaurantDetailsNew.setPerTableBookingPrice(restaurantDetails.getPerTableBookingPrice());
		}
		restaurantDetailsRepository.save(restaurantDetailsNew);
		
		return "Table Details Updated";
	}

}
