package com.freshersProject.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.repository.ItemsRepository;
import com.freshersProject.ecommerce.serviceImpl.ItemsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestItemsService {

	@InjectMocks
	private ItemsServiceImpl itemsServiceImpl;

	@Mock
	private ItemsRepository itemsRepository;

	List<Items> list;

	@BeforeEach
	public void allData() {
		list=new ArrayList<>();

		Items items=new Items();
		items.setRestaurantName("Meghana Foods");
		items.setItemName("Veg Biryani");
		items.setItemPrice(240);
		items.setItemType("Biryani");
		items.setItemDiscount(5);

		Items items1=new Items();
		items1.setRestaurantName("Mehfil");
		items1.setItemName("Mutton Biryani");
		items1.setItemPrice(160);
		items1.setItemType("Biryani");
		items1.setItemDiscount(10);

		list.add(items);
		list.add(items1);
	}

	@Test
	public void testGetAllItems() {
		when(itemsRepository.findAllByItemPrice()).thenReturn(list);
		assertEquals(2,itemsServiceImpl.getAllItems().size());
	}


	@Test
	public void testListAll() {
		String itemName="Veg Biryani";
		when(itemsRepository.findAllByItemName(itemName)).thenReturn(list);
		assertEquals(2,itemsServiceImpl.listAll(itemName).size());
	}


	@Test
	public void testSortByPriceAsc() {
		when(itemsRepository.findAllByitemPrice()).thenReturn(list);
		assertEquals(2,itemsServiceImpl.sortByPriceAsc().size());	
	}


	@Test
	public void testSortByPriceDsc(){
		when(itemsRepository.findAllByItemPrice()).thenReturn(list);
		assertEquals(2,itemsServiceImpl.sortByPriceDsc().size());	
	}

}
