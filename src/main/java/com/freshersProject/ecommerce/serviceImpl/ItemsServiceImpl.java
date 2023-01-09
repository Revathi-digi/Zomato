package com.freshersProject.ecommerce.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.repository.ItemsRepository;
import com.freshersProject.ecommerce.responseBean.ItemsBean;
import com.freshersProject.ecommerce.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService{

	private ItemsRepository itemsRepository;

	public ItemsServiceImpl(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	//search by items
	@Override
	public List<ItemsBean> listAll(String itemName) {
		List<ItemsBean> itemList=new ArrayList<ItemsBean>();
		List<Items> list=new ArrayList<>();
		itemsRepository.findAllByItemName(itemName).forEach(item->list.add(item));
		for(Items items:list) {
			ItemsBean itemBean=new ItemsBean();
			itemBean.setRestaurantName(items.getRestaurantName());
			itemBean.setItemName(items.getItemName());
			itemBean.setItemPrice(items.getItemPrice());
			itemBean.setItemDiscount(items.getItemDiscount());
			itemList.add(itemBean);
		}	

		return itemList;
	}

	// method another 
	//	list.stream().forEach((x)->
	//	{
	//		ItemsBean itemBean= new ItemsBean();
	//		BeanUtils.copyProperties(x, itemBean);
	//		itemList.add(itemBean);
	//	}
	//);


	//sorting by item price in ASC
	@Override
	public List<ItemsBean> sortByPriceAsc() {
		List<ItemsBean> itemList=new ArrayList<>();
		List<Items> list=new ArrayList<>();
		itemsRepository.findAllByitemPrice().forEach(items->list.add(items));
		for(Items items:list) {
			ItemsBean itemsBean=new ItemsBean();
			itemsBean.setRestaurantName(items.getRestaurantName());
			itemsBean.setItemName(items.getItemName());
			itemsBean.setItemPrice(items.getItemPrice());
			itemsBean.setItemDiscount(items.getItemDiscount());
			itemList.add(itemsBean);
		}
		return itemList;
	}

	//sorting by item price in DSC
	@Override
	public List<ItemsBean> sortByPriceDsc() {
		List<ItemsBean> itemList=new ArrayList<>();
		List<Items> list=new ArrayList<>();
		itemsRepository.findAllByItemPrice().forEach(item->list.add(item));
		for(Items items:list) {
			ItemsBean itemsBean=new ItemsBean();
			itemsBean.setRestaurantName(items.getRestaurantName());
			itemsBean.setItemName(items.getItemName());
			itemsBean.setItemPrice(items.getItemPrice());
			itemsBean.setItemDiscount(items.getItemDiscount());
			itemList.add(itemsBean);
		}
		return itemList;
	}

	//user viewing all items
	@Override
	public List<ItemsBean> getAllItems() {

		List<ItemsBean> itemList=new ArrayList<>();
		List<Items> list=new ArrayList<>();
		itemsRepository.findAllByItemPrice().forEach(item->list.add(item));
		for(Items items:list) {
			ItemsBean itemsBean=new ItemsBean();
			itemsBean.setRestaurantName(items.getRestaurantName());
			itemsBean.setItemName(items.getItemName());
			itemsBean.setItemPrice(items.getItemPrice());
			itemsBean.setItemDiscount(items.getItemDiscount());
			itemList.add(itemsBean);
		}
		return itemList;
	}
}
