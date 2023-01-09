package com.freshersProject.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freshersProject.ecommerce.model.TableBooking;
import com.freshersProject.ecommerce.service.TableBookingService;

@RestController
public class TableBookingController {
	@Autowired
	private TableBookingService tableBookingService;

	@PostMapping(value = "/tableBooking")
	public void addBookingDetails(@RequestBody TableBooking tableBooking) {
		tableBookingService.addBookingDetails(tableBooking);

	}

	@GetMapping(value = "/bookTablesData")
	public List<TableBooking> getAlldata() {
		List<TableBooking> list = tableBookingService.getAlldata();

		return list;
	}

	@DeleteMapping("/cancleBooking")
	public void cancleBooking(@RequestParam int bookingNumber) {

		tableBookingService.cancleBooking(bookingNumber);

	}

	@GetMapping(value = "/tableBookingBill/{bookingNumber}")
	private TableBooking getBillById(@PathVariable int bookingNumber) {
		return tableBookingService.getBillById(bookingNumber);

	}

	@PutMapping("/restaurantRating")
	public String restaurantRating(@RequestBody TableBooking tableBooking) {
		return tableBookingService.restaurantRating(tableBooking);
	}

}
