package com.freshersProject.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freshersProject.ecommerce.controller.OrderController;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.requestBean.OrderRequestBean;
import com.freshersProject.ecommerce.requestBean.OrderStatusRequestBean;
import com.freshersProject.ecommerce.requestBean.PaymentOrderRequestBean;
import com.freshersProject.ecommerce.requestBean.RatingRequestBean;
import com.freshersProject.ecommerce.service.OrderService;


@WebMvcTest(OrderController.class)
public class TestOrdersController {

	private	OrderRequestBean orderRequestBean;
	private PaymentOrderRequestBean paymentOrderRequestBean;
	private OrderStatusRequestBean orderStatusRequestBean;
	private RatingRequestBean ratingRequestBean;
    private Orders orders;
    
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService orderService;
	private ObjectMapper objectMapper;

	@Test
	public void getOrderHistoryDateByAsc() throws Exception {
		orderRequestBean=new OrderRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/getOrderHistoryDateByAsc").header("userEmail", "vivek@gmail.com")
				.content(objectMapper.writeValueAsString(orderRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getOrderHistoryDateByDsc() throws Exception {
		orderRequestBean=new OrderRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/getOrderHistoryDateByDsc").header("userEmail", "vivek@gmail.com")
				.content(objectMapper.writeValueAsString(orderRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getUserOrderHistory() throws Exception {
		orderRequestBean=new OrderRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/userViewingOrderHistory").header("userEmail", "vivek@gmail.com")
				.content(objectMapper.writeValueAsString(orderRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getOrderHistoryByPaymentType() throws Exception {
		paymentOrderRequestBean=new PaymentOrderRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/paymentType").header("userEmail", "vivek@gmail.com")
				.header("paymentType", "cash")
				.content(objectMapper.writeValueAsString(paymentOrderRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getOrderHistoryByOrderStatus() throws Exception {
		orderStatusRequestBean=new OrderStatusRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/orderStatus").header("userEmail", "vivek@gmail.com")
				.header("orderStatus", "Deliverd")
				.content(objectMapper.writeValueAsString(orderStatusRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getRating() throws Exception {
		ratingRequestBean=new RatingRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/userRating")
				.header("userEmail", "vivek@gmail.com")
				.header("restaurantName", "Meghana Foods")
				.header("itemRating", 4)
				.content(objectMapper.writeValueAsString(ratingRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test void  trackOrder() throws Exception {
		orders=new Orders();
		objectMapper=new ObjectMapper();
		mockMvc.perform(get("/tracking").param("orderId", "1")
				.content(objectMapper.writeValueAsString(orders)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}



}
