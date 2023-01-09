package com.freshersProject.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.freshersProject.ecommerce.model.Coupons;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.model.RestaurantDetails;
import com.freshersProject.ecommerce.repository.CouponsRepository;
import com.freshersProject.ecommerce.repository.ItemsRepository;
import com.freshersProject.ecommerce.repository.OrdersRepository;
import com.freshersProject.ecommerce.repository.RestaurantDetailsRepository;
import com.freshersProject.ecommerce.requestBean.CouponRequestBean;
import com.freshersProject.ecommerce.requestBean.ItemsRequestBean;
import com.freshersProject.ecommerce.requestBean.TrackUpdateRequestBean;
import com.freshersProject.ecommerce.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {

	private RestaurantDetailsRepository restaurantDetailsRepository;
	private ItemsRepository itemsRepository;
	private CouponsRepository couponsRepository;
	private OrdersRepository orderReposotory;

	public AdminServiceImpl(RestaurantDetailsRepository restaurantDetailsRepository, ItemsRepository itemsRepository,
			CouponsRepository couponsRepository, OrdersRepository orderReposotory) {
		this.restaurantDetailsRepository = restaurantDetailsRepository;
		this.itemsRepository = itemsRepository;
		this.couponsRepository = couponsRepository;
		this.orderReposotory = orderReposotory;
	}

	//inserting restaurant details into DB
	@Override
	public RestaurantDetails saveRestaurantDetails(RestaurantDetails restaurantDetails) {
		return restaurantDetailsRepository.save(restaurantDetails);
	}


	//updating restaurants
	@Override
	public String updateRestaurant(RestaurantDetails restaurantDetails) {
		RestaurantDetails updatigRestautants=restaurantDetailsRepository.findById(restaurantDetails.getRestaurantId());

		if(restaurantDetails.getRestaurantName().isBlank())
		{
			updatigRestautants.setRestaurantName(restaurantDetails.getRestaurantName());
		}
		if(restaurantDetails.getLunchAvlTables()!=0)
		{
			updatigRestautants.setLunchAvlTables(restaurantDetails.getLunchAvlTables());
		}
		if(restaurantDetails.getDinnerAvlTables()!=0)
		{
			updatigRestautants.setDinnerAvlTables(restaurantDetails.getDinnerAvlTables());
		}
		if(restaurantDetails.getPerTableBookingPrice()!=0)
		{
			updatigRestautants.setPerTableBookingPrice(restaurantDetails.getPerTableBookingPrice());
		}
		if(!restaurantDetails.getRestaurantAddress().isBlank())
		{
			updatigRestautants.setRestaurantAddress(restaurantDetails.getRestaurantAddress());
		}

		restaurantDetailsRepository.save(updatigRestautants);
		return "RestaurantDetails  Updated";
	}

	//inserting items into DB through Postman
	@Override
	public Items saveItem(Items item) {
		return itemsRepository.save(item);
	}



	//updating items
	@Override
	public String updateItems(Items items) {
		Items updatingItems=itemsRepository.findById(items.getItemId());

		if(items.getItemPrice()!=0)
		{
			updatingItems.setItemPrice(items.getItemPrice());;	
		}
		if(items.getRestaurantName().isBlank())
		{
			updatingItems.setRestaurantName(items.getRestaurantName());
		}
		if(!items.getItemType().isBlank())
		{
			updatingItems.setItemType(items.getItemType());
		}
		if(!items.getItemName().isBlank())
		{
			updatingItems.setItemName(items.getItemName());
		}
		if(items.getItemDiscount()!=0)
		{
			updatingItems.setItemDiscount(items.getItemDiscount());
		}
		itemsRepository.save(updatingItems);
		return "Items Updated";

	}
	//getting all the details of items
	@Override
	public List<Items> getAll(){
		List<Items> list=new ArrayList<>();
		itemsRepository.findAll().forEach(x->list.add(x));
		return list;
	}

	//deleting items
	@Override
	public void deleteItems(ItemsRequestBean itemsRequestBean) {
		itemsRepository.deleteById(itemsRequestBean.getItemId());		
	}


	//inserting coupons
	@Override
	public Coupons saveCoupons(Coupons coupons) {
		return couponsRepository.save(coupons);

	}

	//deleting coupons
	@Override
	public void deleteCoupon(CouponRequestBean couponRequestBean) {
		couponsRepository.deleteById(couponRequestBean.getCouponId());
	}

	//updating coupons
	@Override
	public String updateCoupons(Coupons coupons) {
		Coupons updatingCoupons=couponsRepository.findById(coupons.getCouponId());

		if(coupons.getCouponAmount()!=0)
		{
			updatingCoupons.setCouponAmount(coupons.getCouponAmount());;	
		}
		if(!coupons.getCouponName().isBlank())
		{
			updatingCoupons.setCouponName(coupons.getCouponName());
		}

		couponsRepository.save(updatingCoupons);
		return "Coupon Updated";

	}


	//inserting ordersHistory
	@Override
	public Orders saveOrderHistory(Orders orders) {
		return orderReposotory.save(orders);
	}

	//getting all the details of order History by admin
	@Override 
	public List<Orders> getAllOrderHistory() {
		List<Orders> list=orderReposotory.findAll();
		return list;
	}

	@Override
	public String updateOrderStatus(TrackUpdateRequestBean trackUpdateRequestBean) {
		Orders order=new Orders();
		try {
		order=orderReposotory.findByOrderId(trackUpdateRequestBean.getOrderId());
		order.setOrderStatus(trackUpdateRequestBean.getOrderStatus());
		orderReposotory.save(order);
		return "Update Success";
		}catch(Exception e) {
			return "Enter Correct Details";
		}
	}




}
