package com.freshersProject.ecommerce.UserModuleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.model.User;
import com.freshersProject.ecommerce.repository.RestaurantDetailsRepository;
import com.freshersProject.ecommerce.repository.UserRepository;
import com.freshersProject.ecommerce.serviceImpl.RestaurantDetailsServiceImpl;
import com.freshersProject.ecommerce.serviceImpl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("junit")
@ExtendWith(MockitoExtension.class)
@Slf4j
public class RestaurantDetailsServiceTest {
	@Mock
	RestaurantDetailsRepository restaurantDetailsRepository;
	@InjectMocks
	RestaurantDetailsServiceImpl restaurantDetailsServiceImpl;
	
	@Test
	public void addTableDetailsTest() throws Exception {
		RestaurantDetails restaurantDetails = new RestaurantDetails(1, "Punjabi", 8, 7, "Jaipur", 300);
		when(restaurantDetailsRepository.save(restaurantDetails)).thenReturn(restaurantDetails);

		assertEquals("table details added successfully", restaurantDetailsServiceImpl.addTableDetails(restaurantDetails));
	}
	
	
	@Test
	public void findAllTest() {
		ArrayList<RestaurantDetails> list = new ArrayList<>();
		RestaurantDetails restaurantDetails = new RestaurantDetails();
		restaurantDetails.setRestaurantId(2);

	list.add(restaurantDetails);
		when(restaurantDetailsRepository.findAll()).thenReturn(list);
		assertEquals(1, restaurantDetailsRepository.findAll().size());
	}
	
	
	@Test
	public void updateTableDetailTest() throws Exception {
		ArrayList<RestaurantDetails> list = new ArrayList<>();
		RestaurantDetails restaurantDetails = new RestaurantDetails();

		restaurantDetails.setRestaurantName("Shahi Bhoj");
		restaurantDetails.setRestaurantAddress("Kota");
		restaurantDetails.setPerTableBookingPrice(100);
		restaurantDetails.setLunchAvlTables(7);
		restaurantDetails.setDinnerAvlTables(7);
		

		list.add(restaurantDetails);

		when(restaurantDetailsRepository.findByRestaurantName("Shahi Bhoj")).thenReturn(restaurantDetails);
		assertEquals("Table Details Updated", restaurantDetailsServiceImpl.updateTableDetail(restaurantDetails));

	}

}
