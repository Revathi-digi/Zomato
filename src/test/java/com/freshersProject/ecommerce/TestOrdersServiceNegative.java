package com.freshersProject.ecommerce;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.repository.OrdersRepository;
import com.freshersProject.ecommerce.requestBean.OrderRequestBean;
import com.freshersProject.ecommerce.requestBean.OrderStatusRequestBean;
import com.freshersProject.ecommerce.requestBean.PaymentOrderRequestBean;
import com.freshersProject.ecommerce.requestBean.RatingRequestBean;
import com.freshersProject.ecommerce.responseBean.OrdersBean;
import com.freshersProject.ecommerce.responseBean.TrackingBean;
import com.freshersProject.ecommerce.serviceImpl.OrderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOrdersServiceNegative {

	@InjectMocks
	OrderServiceImpl orderServiceImpl;

	@Mock
	OrdersRepository orderRepository; 

	Orders orders;
	Orders order;
	RatingRequestBean ratingRequestBean;
	OrderRequestBean orderRequestBean;
	OrdersBean ordersBean;
	PaymentOrderRequestBean paymentOrderRequestBean;
	OrderStatusRequestBean orderStatusRequestBean;
	TrackingBean trackingBean;

	@BeforeEach
	public void allData() {

		orders=new Orders();
		orders.setOrderId(1);
		orders.setRestaurantName("Narmada");
		orders.setItemName("Veg biryani");
		orders.setOrderTotal(370);
		orders.setOrderStatus("Ready to Delivery");
		orders.setOrderPaymentStatus("Paid");
		orders.setOrderDate(Calendar.getInstance().getTime());
		orders.setPaymentType("Cash");
		orders.setUserEmail("revathi@gmail.com");


		order=new Orders();
		order.setOrderId(2);
		order.setRestaurantName("Mehfil");
		order.setItemName("Veg biryani");
		order.setOrderTotal(270);
		order.setOrderStatus("Deliverd");
		order.setOrderPaymentStatus("Paid");
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setPaymentType("Card");
		order.setUserEmail("vivek@gmail.com");
		order.setItemRating(4);

		ratingRequestBean=new RatingRequestBean();
		ratingRequestBean.setUserEmail("vivek@gmail.com");
		ratingRequestBean.setRestaurantName("Narmada");
		ratingRequestBean.setItemRating(4);

		orderRequestBean=new OrderRequestBean();
		orderRequestBean.setUserEmail("vivek@gmail.com");

		ordersBean=new OrdersBean();

		paymentOrderRequestBean=new PaymentOrderRequestBean();
		paymentOrderRequestBean.setUserEmail("vivek@gmail.com");
		paymentOrderRequestBean.setPaymentType("Cash");

		orderStatusRequestBean=new OrderStatusRequestBean();
		orderStatusRequestBean.setUserEmail("vivek@gmail.com");
		orderStatusRequestBean.setOrderStatus("Deliverd");

		trackingBean=new TrackingBean();

	}

	@Test
	public void testGetOrderHistoryDateByAsc() {
		when(orderRepository.findAllByuserEmail(orderRequestBean.getUserEmail())).thenReturn(Stream.of(orders,order).collect(Collectors.toList()));
		assertNotEquals(orders,orderServiceImpl.getOrderHistoryDateByAsc(orderRequestBean).size());

	}

	@Test
	public void testGetOrderHistoryDateByDsc() {
		when(orderRepository.findAllByuserEmail(orderRequestBean.getUserEmail())).thenReturn(Stream.of(orders,order).collect(Collectors.toList()));
		assertNotEquals(orders,orderServiceImpl.getOrderHistoryDateByDsc(orderRequestBean).size());

	}

	@Test
	public void testGetOderHistory() {
		when(orderRepository.findAllByuserEmail(orderRequestBean.getUserEmail())).thenReturn(Stream.of(orders,order).collect(Collectors.toList()));
		assertNotEquals(orders,orderServiceImpl.getOderHistory(orderRequestBean).get(1).getUserEmail());
	}

	@Test
	public void TestGetOrderHistoryByPaymentType() {
		when(orderRepository.findAllByuserEmail(paymentOrderRequestBean.getUserEmail())).thenReturn(Stream.of(orders,order).collect(Collectors.toList()));
		assertNotEquals(orders, orderServiceImpl.getOrderHistoryByPaymentType(paymentOrderRequestBean).get(0).getPaymentType());

	}	

	@Test
	public void  testGetOrderHistoryByOrderStatus() {
		when(orderRepository.findAllByuserEmail(orderStatusRequestBean.getUserEmail())).thenReturn(Stream.of(orders,order).collect(Collectors.toList()));
		assertNotEquals(orders,orderServiceImpl.getOrderHistoryByOrderStatus(orderStatusRequestBean).get(0).getOrderStatus());

	}	

	@Test
	public void testTrack() {
		int id=1;
		when(orderRepository.findByOrderId(id)).thenReturn(orders);
		assertNotEquals(6,orderServiceImpl.track(id).getOrderId());
	}

	@Test
	public void testGetRating() {
		when(orderRepository.findByUserEmailAndRestaurantName(ratingRequestBean.getUserEmail(),ratingRequestBean.getRestaurantName())).thenReturn(orders);
		when(orderRepository.save(orders)).thenReturn(orders);
		assertNotEquals(4, orderServiceImpl.getRating(ratingRequestBean));

	}
}
