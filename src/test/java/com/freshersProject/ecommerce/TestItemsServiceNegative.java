package com.freshersProject.ecommerce;

import static org.junit.Assert.assertNotEquals;
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
public class TestItemsServiceNegative {



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
		assertNotEquals(list,itemsServiceImpl.getAllItems().size());

	}


	@Test
	public void testListAll() {
		String itemName="Veg Biryani";
		when(itemsRepository.findAllByItemName(itemName)).thenReturn(list);
		assertNotEquals(1,itemsServiceImpl.listAll(itemName).size());
	}


	@Test
	public void testSortByPriceAsc() {
		when(itemsRepository.findAllByitemPrice()).thenReturn(list);
		assertNotEquals(0,itemsServiceImpl.sortByPriceAsc().size());	
	}


	@Test
	public void testSortByPriceDsc(){
		when(itemsRepository.findAllByItemPrice()).thenReturn(list);
		assertNotEquals(0,itemsServiceImpl.sortByPriceDsc().size());	

	}
}
