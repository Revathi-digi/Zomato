package com.freshersProject.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.model.TableBooking;
import com.freshersProject.ecommerce.repository.RestaurantDetailsRepository;
import com.freshersProject.ecommerce.repository.TableBookingRepository;
import com.freshersProject.ecommerce.service.TableBookingService;

@Service
@Transactional
public class TableBookingServiceImpl implements TableBookingService {
	@Autowired
	private TableBookingRepository tableBookingRepository;
	@Autowired
	private RestaurantDetailsRepository restaurantDetailsRepository;

	@Override
	public String addBookingDetails(TableBooking tableBooking) {

		String restaurantName = tableBooking.getRestaurantName();
		RestaurantDetails restaurantDetails = restaurantDetailsRepository.findByRestaurantName(restaurantName);
		int avlDinner = restaurantDetails.getDinnerAvlTables();
		int avlLunch = restaurantDetails.getLunchAvlTables();
		int amountpaid = (restaurantDetails.getPerTableBookingPrice()) * (tableBooking.getNumberOfTables());

		if (tableBooking.getLunchOrdinner().equals("Lunch")) {

			if (avlLunch > 0) {

				avlLunch = avlLunch - tableBooking.getNumberOfTables();
				if (avlLunch >= 0) {
					restaurantDetails.setLunchAvlTables(avlLunch);

					restaurantDetailsRepository.save(restaurantDetails);
					

					tableBookingRepository.save(tableBooking);
					tableBooking.setAmountPaid(amountpaid);
					
				} else
					System.out.println("No available tables");
			}

		}

		if (tableBooking.getLunchOrdinner().equals("Dinner")) {

			if (avlDinner > 0) {

				avlDinner = avlDinner - tableBooking.getNumberOfTables();
				if (avlDinner >= 0) {

					restaurantDetails.setDinnerAvlTables(avlDinner);
					restaurantDetailsRepository.save(restaurantDetails);
					tableBookingRepository.save(tableBooking);
					tableBooking.setAmountPaid(amountpaid);
					
				} else
					System.out.println("No available tables");
			}

		}
		return "Details Added";

	}

	@Override
	public List<TableBooking> getAlldata() {
		List<TableBooking> tableBooking = new ArrayList<>();
		tableBookingRepository.findAll().forEach(tableBook -> tableBooking.add(tableBook));
		return tableBooking;
	}

	@Override
	public void cancleBooking(int bookingNumber) {
		int avlDinner;
		int avlLunch;
		TableBooking tableBooking = tableBookingRepository.findById(bookingNumber);
		RestaurantDetails restaurantDetails = restaurantDetailsRepository
				.findByRestaurantName(tableBooking.getRestaurantName());

		avlDinner = restaurantDetails.getDinnerAvlTables();
		avlLunch = restaurantDetails.getLunchAvlTables();

		System.out.println(tableBooking.getLunchOrdinner());
		if (tableBooking.getLunchOrdinner().equalsIgnoreCase("Lunch")
				&& tableBooking.getRestaurantName().equals(restaurantDetails.getRestaurantName())) {
			avlLunch = avlLunch + tableBooking.getNumberOfTables();
			restaurantDetails.setLunchAvlTables(avlLunch);
			restaurantDetailsRepository.save(restaurantDetails);
			tableBookingRepository.deleteById(bookingNumber);
		} else {
			if (tableBooking.getLunchOrdinner().equalsIgnoreCase("Dinner")
					&& tableBooking.getRestaurantName().equals(restaurantDetails.getRestaurantName())) {
				avlDinner = avlDinner + tableBooking.getNumberOfTables();
				restaurantDetails.setDinnerAvlTables(avlDinner);
				restaurantDetailsRepository.save(restaurantDetails);
				tableBookingRepository.deleteById(bookingNumber);
			}
		}

	}

	@Override
	public TableBooking getBillById(int bookingNumber) {

		return tableBookingRepository.findById(bookingNumber);
	}

	@Override
	public String restaurantRating(TableBooking tableBooking) {
		TableBooking tableBook = tableBookingRepository.findById(tableBooking.getBookingNumber());
		if (tableBooking.getRestaurantRating() != 0) {
			tableBook.setRestaurantRating(tableBooking.getRestaurantRating());
		}
		tableBookingRepository.save(tableBook);
		return "Rating Done";
	}

}
