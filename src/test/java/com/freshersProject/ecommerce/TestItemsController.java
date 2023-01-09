package com.freshersProject.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.freshersProject.ecommerce.controller.ItemsController;
import com.freshersProject.ecommerce.service.ItemsService;

@WebMvcTest(ItemsController.class)
public class TestItemsController {


	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ItemsService itemsService;

	@Test
	public void listAll() throws Exception {
		mockMvc.perform(get("/search").param("itemName", "Veg Biryani")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getAllItems() throws Exception {
		mockMvc.perform(get("/viewAllItems")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void sortByPriceAsc() throws Exception {
		mockMvc.perform(get("/sortingItemPriceByAsc")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void sortByPriceDsc() throws Exception {
		mockMvc.perform(get("/sortingItemPriceByDsc")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}


}
