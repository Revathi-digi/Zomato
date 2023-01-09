package com.freshersProject.ecommerce.service;

import java.util.List;

import com.freshersProject.ecommerce.model.TableBooking;

public interface TableBookingService {

	String addBookingDetails(TableBooking tableBooking);

	List<TableBooking> getAlldata();

	void cancleBooking(int bookingNumber);

	TableBooking getBillById(int bookingNumber);

	String restaurantRating(TableBooking tableBooking);

}
