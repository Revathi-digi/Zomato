package com.freshersProject.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.freshersProject.ecommerce.model.TableBooking;

@EnableJpaRepositories
public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {

	TableBooking findByrestaurantName(String restaurantName);

	TableBooking findById(int bookingNumber);

}
