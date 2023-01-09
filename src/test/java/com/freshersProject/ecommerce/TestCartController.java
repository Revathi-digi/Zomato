package com.freshersProject.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.freshersProject.ecommerce.controller.CartController;
import com.freshersProject.ecommerce.requestBean.AddCartRequestBean;
import com.freshersProject.ecommerce.requestBean.PayRequestBean;
import com.freshersProject.ecommerce.service.CartService;

@WebMvcTest(CartController.class)
public class TestCartController {

	private AddCartRequestBean addCartRequestBean;	
	private PayRequestBean payRequestBean;


	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CartService cartService;
	private ObjectMapper objectMapper;

	@Test
	public void addCart() throws Exception {
		addCartRequestBean=new AddCartRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/addCart")
				.header("userEmail", "vivek@gmail.com")
				.header("restaurantName", "Mehfil")
				.header("itemName", "Meals")
				.content(objectMapper.writeValueAsString(addCartRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void userCart() throws Exception {
		mockMvc.perform(get("/viewUserCart").param("userEmail", "vivek@gmail.com")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void viewCart() throws Exception {
		mockMvc.perform(get("/viewCart")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteOne() throws Exception {
		addCartRequestBean=new AddCartRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(delete("/deleteOne")
				.header("userEmail", "vivek@gmail.com")
				.header("restaurantName", "Mehfil")
				.header("itemName", "Meals")
				.content(objectMapper.writeValueAsString(addCartRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteAll() throws Exception {
		mockMvc.perform(delete("/deleteAll")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void pay() throws Exception {
		payRequestBean=new PayRequestBean();
		objectMapper=new ObjectMapper();
		mockMvc.perform(post("/pay")
				.header("userEmail", "vivek@gmail.com")
				.header("payType", "Cash")
				.content(objectMapper.writeValueAsString(payRequestBean)).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
