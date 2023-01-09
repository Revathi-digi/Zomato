package com.freshersProject.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.freshersProject.ecommerce.model.Orders;


@Repository
@EnableJpaRepositories
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
	
	Orders findByOrderId(int id);

	List<Orders> findAllByuserEmail(String userEmail);

	Orders findByUserEmailAndRestaurantName(String userEmail, String restaurantName);
}
