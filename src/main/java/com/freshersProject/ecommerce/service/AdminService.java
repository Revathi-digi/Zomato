package com.freshersProject.ecommerce.service;

import java.util.List;

import com.freshersProject.ecommerce.model.Coupons;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.requestBean.CouponRequestBean;
import com.freshersProject.ecommerce.requestBean.ItemsRequestBean;
import com.freshersProject.ecommerce.requestBean.TrackUpdateRequestBean;

public interface AdminService {
	
	Items saveItem(Items item);
	List<Items> getAll();
	String updateItems(Items items);
	void deleteItems(ItemsRequestBean itemsRequestBean);
	
	Coupons saveCoupons(Coupons coupons);
	void deleteCoupon(CouponRequestBean couponRequestBean);
	String updateCoupons(Coupons coupons);
	
	List<Orders> getAllOrderHistory();
	Orders saveOrderHistory(Orders orderHistory);
	String updateOrderStatus(TrackUpdateRequestBean trackUpdateRequestBean);
	
	String updateRestaurant(RestaurantDetails restaurant);
	RestaurantDetails saveRestaurantDetails(RestaurantDetails restaurantDetails);

}
