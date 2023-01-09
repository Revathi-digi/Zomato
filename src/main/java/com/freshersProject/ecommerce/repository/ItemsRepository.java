package com.freshersProject.ecommerce.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.model.RestaurantDetails;


@Repository
@EnableJpaRepositories
public interface ItemsRepository extends JpaRepository<Items,Integer> {
	@Query(value="SELECT *  FROM Items  WHERE CONCAT(' ',item_name,' ',item_price,' ',restaurant_name,' ',item_type,' ',item_discount) LIKE %?1%",nativeQuery=true)
	List<Items> findAllByItemName(String itemName);

	@Query(value="Select * from Items ORDER BY item_price",nativeQuery=true)
	List<Items> findAllByitemPrice();


	@Query(value="Select * from Items ORDER BY item_price desc",nativeQuery=true)
	List<Items> findAllByItemPrice();


	List<RestaurantDetails> findByRestaurantName(String restaurantName);


	Items findByRestaurantNameAndItemName(String restaurantName,String itemName);

	Items findByItemName(String itemName);
	
	Items findById(int itemId);
	

}
