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
import com.freshersProject.ecommerce.model.TableBooking;
import com.freshersProject.ecommerce.repository.TableBookingRepository;
import com.freshersProject.ecommerce.serviceImpl.RestaurantDetailsServiceImpl;
import com.freshersProject.ecommerce.serviceImpl.TableBookingServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("junit")
@ExtendWith(MockitoExtension.class)
@Slf4j
public class TableBookingServiceTest {
	@Mock
	TableBookingRepository tableBookingRepository;
	@InjectMocks
	TableBookingServiceImpl tableBookingServiceImpl;
	@InjectMocks
	RestaurantDetailsServiceImpl restaurantDetailsServiceImpl;

/*	@Test
	public void addBookingDetailsTest() throws Exception {
		TableBooking tableBooking = new TableBooking(1, "Asha", "Food Place", "Lunch", 1, "6350305264", 1, 100, 4.5);
		when(tableBookingRepository.save(tableBooking)).thenReturn(tableBooking);
		RestaurantDetails restaurantDetails = new RestaurantDetails();

		assertEquals("Details Added", tableBookingServiceImpl.addBookingDetails(tableBooking));
	}*/

	@Test
	public void findAllTest() {
		ArrayList<TableBooking> list = new ArrayList<>();
		TableBooking tableBooking = new TableBooking();
		tableBooking.setBookingNumber(2);

		list.add(tableBooking);
		when(tableBookingRepository.findAll()).thenReturn(list);
		assertEquals(1, tableBookingRepository.findAll().size());
	}

}
