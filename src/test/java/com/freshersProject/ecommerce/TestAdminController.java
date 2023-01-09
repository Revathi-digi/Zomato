package com.freshersProject.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freshersProject.ecommerce.controller.AdminController;
import com.freshersProject.ecommerce.model.Coupons;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.requestBean.CouponRequestBean;
import com.freshersProject.ecommerce.requestBean.ItemsRequestBean;
import com.freshersProject.ecommerce.requestBean.TrackUpdateRequestBean;
import com.freshersProject.ecommerce.service.AdminService;

@WebMvcTest(AdminController.class)
public class TestAdminController {

	private Items items;
	private Orders orders;
	private Coupons coupons;

	private RestaurantDetails restaurantDetails;
	private ItemsRequestBean itemsRequestBean;
	private CouponRequestBean couponRequestBean;
	private TrackUpdateRequestBean trackUpdateRequestBean;


	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AdminService AdminService;
	private ObjectMapper objectMapper;

	

	@Test
	public void saveRestaurantDetails() throws Exception {
		restaurantDetails=new RestaurantDetails();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/addRestaurantDetails")
				.header("restaurantId", 1)
				.header("restaurantName", "Mehfil")
				.header("lunchAvlTables", 20)
				.header("dinnerAvlTables", 34)
				.header("restaurantAddress", "Koramangala 5th Block, Bangalore ")
				.header("perTableBookingPrice", 460)
				.content(objectMapper.writeValueAsString(restaurantDetails)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateRestaurant() throws Exception {
		restaurantDetails=new RestaurantDetails();
		objectMapper=new ObjectMapper();
		mockMvc.perform(put("/updateRestaurant")
				.header("restaurantId", 1)
				.header("restaurantName", "Mehfil")
				.header("lunchAvlTables", 20)
				.header("dinnerAvlTables", 34)
				.header("restaurantAddress", "Koramangala 5th Block, Bangalore ")
				.header("perTableBookingPrice", 460)
				.content(objectMapper.writeValueAsString(restaurantDetails)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void saveItem() throws Exception {
		items=new Items();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/saveItems")
				.header("itemId", 1)
				.header("restaurantName", "Mehfil")
				.header("itemName", "Veg Biryani")
				.header("itemPrice", 340)
				.header("itemType", "Biryani")
				.header("itemDiscount", 10)
				.content(objectMapper.writeValueAsString(items)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteItems() throws Exception {
		itemsRequestBean=new ItemsRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(delete("/deleteItems")
				.header("itemId", 1)
				.content(objectMapper.writeValueAsString(itemsRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateItems() throws Exception {
		items=new Items();
		objectMapper=new ObjectMapper();
		mockMvc.perform(put("/updateItems")
				.header("itemId", 1)
				.header("restaurantName", "Mehfil")
				.header("itemName", "Veg Biryani")
				.header("itemPrice", 340)
				.header("itemType", "Biryani")
				.header("itemDiscount", 10)
				.content(objectMapper.writeValueAsString(items)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getAllItems() throws Exception {
		mockMvc.perform(get("/getAllItems")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void saveCoupons() throws Exception {
		coupons=new Coupons();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/addCoupons")
				.header("couponId", 1)
				.header("couponName", "ZOMATO")
				.header("couponAmount", 200)
				.header("billReduce", "For above RS.1000")
				.content(objectMapper.writeValueAsString(coupons)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateCoupons() throws Exception {
		coupons=new Coupons();
		objectMapper=new ObjectMapper();
		mockMvc.perform(put("/updateCoupons")
				.header("couponId", 1)
				.header("couponName", "ZOMATO")
				.header("couponAmount", 200)
				.header("billReduce", "For above RS.1000")
				.content(objectMapper.writeValueAsString(coupons)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteCoupons() throws Exception {
		couponRequestBean=new CouponRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(delete("/deleteCoupons")
				.header("couponId", 1)
				.content(objectMapper.writeValueAsString(couponRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void saveOrderHistory() throws Exception {
		orders=new Orders();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/addOrderHistory")
				.header("orderId", 1)
				.header("userEmail", "vivek@gmail.com")
				.header("restaurantName", "Mehfil")
				.header("itemName", "Veg Biryani")
				.header("orderTotal", 440)
				.header("orderStatus", "Deliverd")
				.header("orderPaymentStatus", "Paid")
				.header("paymentType", "Card")
				.header("orderDate", 1998-01-30)
				.header("itemRating", 10)
				.content(objectMapper.writeValueAsString(orders)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getAllOrderHistory() throws Exception {
		mockMvc.perform(get("/getAllUsersOderHistory")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void trackingUpdate() throws Exception {
		trackUpdateRequestBean=new TrackUpdateRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/trackingUpdate")
				.header("orderId", 1)
				.header("orderStatus", "Delivering in 30 mins")
				.content(objectMapper.writeValueAsString(trackUpdateRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
