package com.freshersProject.ecommerce.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.freshersProject.ecommerce.responseBean.ItemsBean;
import com.freshersProject.ecommerce.service.ItemsService;

@RestController
public class ItemsController {

	private ItemsService itemsService;

	public ItemsController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	//search Bar
	@GetMapping(value="/search")
	public List<ItemsBean> viewHomePage(@RequestParam String itemName) {
		List<ItemsBean> itemList =new ArrayList<>(); 
		itemList=itemsService.listAll(itemName);
		return itemList;
	}

	//get all items
	@GetMapping("/viewAllItems")
	public List<ItemsBean> getAllItems(){
		List<ItemsBean> list=itemsService.getAllItems();
		return list;
	}

	//items ascending order by price
	@GetMapping("/sortingItemPriceByAsc")
	public List<ItemsBean> getOrderWithSortAsc(){
		List<ItemsBean> list=itemsService.sortByPriceAsc();
		return list;
	}

	//items Descending order by price
	@GetMapping("/sortingItemPriceByDsc")
	public List<ItemsBean> getOrderWithSortDsc(){
		List<ItemsBean> list=itemsService.sortByPriceDsc();
		return list;
	}
}
