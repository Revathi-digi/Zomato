package com.freshersProject.ecommerce.service;

import java.util.List;

import com.freshersProject.ecommerce.responseBean.ItemsBean;

public interface ItemsService {

	List<ItemsBean> listAll(String userEmail);
	
	List<ItemsBean> sortByPriceAsc();
	
	List<ItemsBean> sortByPriceDsc();

	List<ItemsBean> getAllItems();
}
