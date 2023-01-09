package com.freshersProject.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freshersProject.ecommerce.model.Coupons;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.requestBean.CouponRequestBean;
import com.freshersProject.ecommerce.requestBean.ItemsRequestBean;
import com.freshersProject.ecommerce.requestBean.TrackUpdateRequestBean;
import com.freshersProject.ecommerce.service.AdminService;

@RestController
public class AdminController {

	private AdminService adminService;

	public AdminController(AdminService adminService){
		this.adminService = adminService;
	}
	//inserting details of restaurant into DB through postman by admin
	@PostMapping("/addRestaurantDetails")  
	public RestaurantDetails saveRestaurantDetails(@RequestBody RestaurantDetails restaurantDetails) {
		adminService.saveRestaurantDetails(restaurantDetails);
		return restaurantDetails;
	}
	//updating Restaurants by admin
	@PutMapping("/updateRestaurant")
	public String updateRestaurant(@RequestBody RestaurantDetails restaurant) {
		return adminService.updateRestaurant(restaurant);
	}

	//inserting items into DB through postman/swagger
	@PostMapping("/saveItems")  
	public Items saveItem(@RequestBody Items item) {
		adminService.saveItem(item);
		return item;
	}

	//deleting items
	@DeleteMapping("/deleteItems")
	public String deleteItems(@RequestBody ItemsRequestBean itemsRequestBean) {
		adminService.deleteItems(itemsRequestBean);
		return "Item Deleted";	
	}

	//updating items
	@PutMapping("/updateItems")
	public String updateItems(@RequestBody Items items) {
		return adminService.updateItems(items);
	}

	//getting all the Items
	@GetMapping("/getAllItems")
	public List<Items> getAllItems(){
		List<Items> list=adminService.getAll();
		return list;
	}

	//inserting Coupons into DB through postman
	@PostMapping("/addCoupons")  
	public Coupons saveCoupons(@RequestBody Coupons coupons) {
		adminService.saveCoupons(coupons);
		return coupons;
	}

	//updating Coupons  by admin
	@PutMapping("/updateCoupons")
	public String updateCoupons(@RequestBody Coupons coupons) {
		return adminService.updateCoupons(coupons);
	}


	//deleting Coupons by admin
	@DeleteMapping("/deleteCoupons")
	public String deleteCoupons(@RequestBody CouponRequestBean couponRequestBean) {
		adminService.deleteCoupon(couponRequestBean);
		return "Coupon Deleted";	
	}

	//inserting orderhistory  into DB through postman/swagger
	@PostMapping("/addOrderHistory")  
	public Orders saveOrderHistory(@RequestBody Orders orders) {
		adminService.saveOrderHistory(orders);
		return orders;
	}

	//getting all the details of order history 
	@GetMapping("/getAllUsersOderHistory")
	public List<Orders> getAll(){
		List<Orders> list=adminService.getAllOrderHistory();
		return list;
	}

	@PostMapping("/trackingUpdate")
	public String trackUpdate(@RequestBody TrackUpdateRequestBean trackUpdateRequestBean) {
		return adminService.updateOrderStatus(trackUpdateRequestBean);
	}

}
