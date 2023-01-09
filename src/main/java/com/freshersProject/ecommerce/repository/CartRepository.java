package com.freshersProject.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.freshersProject.ecommerce.model.Cart;

@EnableJpaRepositories
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	String findByRestaurantName(String restaurantName);

	List<Cart> findAllByUserEmail(String userEmail);
	
	@Query(value="select * from cart where user_email=?1 and restaurant_name=?2 and item_name=?3",nativeQuery = true)
	Cart findByUserEmailAndRestaurantNameAndItemName(String userEmail,String restaurantName,String itemName);
}
