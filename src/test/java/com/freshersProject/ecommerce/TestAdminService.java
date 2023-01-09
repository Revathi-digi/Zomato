package com.freshersProject.ecommerce;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
import com.freshersProject.ecommerce.serviceImpl.AdminServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdminService {

	@InjectMocks
	private AdminServiceImpl adminServiceImpl;

	@Mock
	private RestaurantDetailsRepository restaurantDetailsRepository;

	@Mock
	private CouponsRepository couponsRepository;

	@Mock
	private OrdersRepository orderReposotory;

	@Mock
	private ItemsRepository itemsRepository;

	private Items items;
	private Items item;
	private Coupons coupons;
	private Coupons coupon;
	private RestaurantDetails restaurantDetails;
	private RestaurantDetails restaurantDetail;
	Orders orders;

	@BeforeEach
	public void update() {
		items=new Items();
		items.setItemId(1);
		items.setRestaurantName("Narmada");
		items.setItemName("Egg Biryani");
		items.setItemPrice(260);
		items.setItemType("Biryani");
		items.setItemDiscount(10);

		item=new Items();
		item.setItemId(1);	
		item.setRestaurantName("Narmada");
		item.setItemName("Veg Biryani");
		item.setItemPrice(260);
		item.setItemType("Biryani");
		item.setItemDiscount(10);

		coupons=new Coupons();
		coupons.setCouponId(1);
		coupons.setCouponAmount(150);
		coupons.setCouponName("ZOMATO");
		coupons.setBillReduce("Above Rs 900");

		coupon=new Coupons();
		coupon.setCouponId(1);
		coupon.setCouponAmount(150);
		coupon.setCouponName("ZOMATO");
		coupon.setBillReduce("Above Rs 1000");

		restaurantDetails=new RestaurantDetails();
		restaurantDetails.setRestaurantId(1);
		restaurantDetails.setRestaurantName("Udupi Grand");
		restaurantDetails.setLunchAvlTables(16);
		restaurantDetails.setDinnerAvlTables(24);
		restaurantDetails.setRestaurantAddress("Koramangala 5th Block Bangalore");
		restaurantDetails.setPerTableBookingPrice(750);

		restaurantDetail=new RestaurantDetails();
		restaurantDetail.setRestaurantId(1);
		restaurantDetail.setRestaurantName("Udupi Grand");
		restaurantDetail.setLunchAvlTables(18);
		restaurantDetail.setDinnerAvlTables(24);
		restaurantDetail.setRestaurantAddress("Koramangala 5th Block Bangalore");
		restaurantDetail.setPerTableBookingPrice(750);	
	}

	@Test
	public void testSaveRestaurantDetails() {
		RestaurantDetails restaurantDetails=new RestaurantDetails(1," Meghana Foods",20,400,"Koramangala,5th Block",10);
		when(restaurantDetailsRepository.save(restaurantDetails)).thenReturn(restaurantDetails);
		assertEquals(restaurantDetails,adminServiceImpl.saveRestaurantDetails(restaurantDetails));
	}

	@Test
	public void testSaveCoupons() {
		Coupons coupons=new Coupons(1,"APPPLY",100," For above Rs.700");
		when(couponsRepository.save(coupons)).thenReturn(coupons);
		assertEquals(coupons,adminServiceImpl.saveCoupons(coupons));
	}

	@Test
	public void testSaveOrderHistory() {
		Orders orders1=new Orders(1,"Vivek@123","Mehfil","Veg Biryani",(float)250.25,"Deliverd","Paid","Cash",Calendar.getInstance().getTime(),(float)4.5);
		when(orderReposotory.save(orders1)).thenReturn(orders1);
		assertEquals(orders1,adminServiceImpl.saveOrderHistory(orders1));
	}

	@Test
	public void testSaveItem() {
		Items items=new Items(1,"Meghana Foods","Mutton Curry",(float)450.50,"Curry",10);
		when(itemsRepository.save(items)).thenReturn(items);
		assertEquals(items,adminServiceImpl.saveItem(items));
	}

	@Test
	public void testDeleteItems()  {
		ItemsRequestBean itemsRequestBean=new ItemsRequestBean(1);
		doNothing().when(itemsRepository).deleteById(1);
		adminServiceImpl.deleteItems(itemsRequestBean);
	}

	@Test
	public void testDeleteCoupons() {
		CouponRequestBean couponRequestBean =new CouponRequestBean(1);
		doNothing().when(couponsRepository).deleteById(1);
		adminServiceImpl.deleteCoupon(couponRequestBean);
	}

	@Test
	public void testUpdateItems(){
		when(itemsRepository.findById(items.getItemId())).thenReturn(item);
		when(itemsRepository.save(item)).thenReturn(item);
		assertEquals("Items Updated",adminServiceImpl.updateItems(items));	
	}

	@Test
	public void testUpdateCoupons() {
		when(couponsRepository.findById(coupons.getCouponId())).thenReturn(coupon);
		when(couponsRepository.save(coupon)).thenReturn(coupon);
		assertEquals("Coupon Updated",adminServiceImpl.updateCoupons(coupons));
	}

	@Test
	public void testUpdateRestaurants() {
		when(restaurantDetailsRepository.findById(restaurantDetails.getRestaurantId())).thenReturn(restaurantDetail);
		when(restaurantDetailsRepository.save(restaurantDetail)).thenReturn(restaurantDetail);
		assertEquals("RestaurantDetails  Updated", adminServiceImpl.updateRestaurant(restaurantDetails));
	}

	@Test
	public void testGetAll() {
		Items items = new Items(1, "Narmada", "Meals",(float)160.50, "Rice", 5);
		Items items1 = new Items(2, "Santosh Dhaba", "Paneer Butter Masala",250, "Curry", 10);
		List<Items> list = new ArrayList<Items>();
		list.add(items);
		list.add(items1);
		when(itemsRepository.findAll()).thenReturn(list);
		assertEquals(2, adminServiceImpl.getAll().size());

	}

	@Test
	public void testGetAllOrderHistory() {
		orders=new Orders(1,"vivek@gmail.com","Nagajuna","Chicken Curry",(float)225.75,"Deliverd","Paid","Card",Calendar.getInstance().getTime(),(float)4.5);
		Orders orders1=new Orders(2,"sai@gmail.com","Udupi Grand","Poori",50,"Deliverd","Paid","Cash",Calendar.getInstance().getTime(),(float)4.0);
		Orders orders2=new Orders(3,"praveen@gmail.com","Santosh Dhaba","Tandoori Roti",150,"Deliverd","Paid","Cash",Calendar.getInstance().getTime(),(float)4.0);
		List<Orders> list=new ArrayList<Orders>();
		list.add(orders);
		list.add(orders1);
		list.add(orders2);
		when(orderReposotory.findAll()).thenReturn(list);
		assertEquals(3, adminServiceImpl.getAllOrderHistory().size());
	}

	@Test
	public void testUpdateOrderStatus() {
		TrackUpdateRequestBean trackUpdateRequestBean=new TrackUpdateRequestBean(1,"Order Picked");
		when(orderReposotory.findByOrderId(trackUpdateRequestBean.getOrderId())).thenReturn(orders);
		when(orderReposotory.save(orders)).thenReturn(orders);
		assertThat(adminServiceImpl.updateOrderStatus(trackUpdateRequestBean)).isNotNull();
	}

}