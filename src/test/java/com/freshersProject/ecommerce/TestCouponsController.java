package com.freshersProject.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.freshersProject.ecommerce.controller.CouponsController;
import com.freshersProject.ecommerce.service.CouponsService;

@WebMvcTest(CouponsController.class)
public class TestCouponsController {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CouponsService CouponsService;

	@Test
	public void getAllCoupons() throws Exception {
		mockMvc.perform(get("/getCoupons")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
