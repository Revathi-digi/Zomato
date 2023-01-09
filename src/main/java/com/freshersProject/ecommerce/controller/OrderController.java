package com.freshersProject.ecommerce.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freshersProject.ecommerce.requestBean.OrderRequestBean;
import com.freshersProject.ecommerce.requestBean.OrderStatusRequestBean;
import com.freshersProject.ecommerce.requestBean.PaymentOrderRequestBean;
import com.freshersProject.ecommerce.requestBean.RatingRequestBean;
import com.freshersProject.ecommerce.responseBean.OrdersBean;
import com.freshersProject.ecommerce.responseBean.TrackingBean;
import com.freshersProject.ecommerce.service.OrderService;

@RestController
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderHistoryService) {
		this.orderService = orderHistoryService;
	}

	//getting order History by DATE ASC
	@PostMapping("/getOrderHistoryDateByAsc")
	public List<OrdersBean> getAsc(@RequestBody OrderRequestBean orderRequestBean){
		List<OrdersBean> orderList=new ArrayList<>();
		orderList=orderService.getOrderHistoryDateByAsc(orderRequestBean);
		return orderList; 
	}

	//getting order History by DATE DSC
	@PostMapping("/getOrderHistoryDateByDsc")
	public List<OrdersBean> getDsc(@RequestBody OrderRequestBean orderRequestBean){
		List<OrdersBean> orderList=new ArrayList<>();
		orderList=orderService.getOrderHistoryDateByDsc(orderRequestBean);
		return orderList; 
	}

	//user viewing his order history by using his email
	@PostMapping("/userViewingOrderHistory")
	public List<OrdersBean> getUserOrderHistory(@RequestBody OrderRequestBean orderRequestBean){
		List<OrdersBean> orderList=new ArrayList<>();
		orderList=orderService.getOderHistory(orderRequestBean);
		return orderList;
	}

	//User filtering by paymentType
	@PostMapping(value="/paymentType")
	public List<OrdersBean> getOrderHistoryByPaymentType(@RequestBody PaymentOrderRequestBean paymentOrderRequestBean){
		List<OrdersBean> orderList=new ArrayList<>();
		orderList=orderService.getOrderHistoryByPaymentType(paymentOrderRequestBean);
		return orderList;
	}

	//User filtering by orderStatus
	@PostMapping(value="/orderStatus")
	public List<OrdersBean> getOrderHistoryByOrderStatus(@RequestBody OrderStatusRequestBean  orderStatusRequestBean){
		List<OrdersBean> orderList=new ArrayList<>();
		orderList=orderService.getOrderHistoryByOrderStatus(orderStatusRequestBean);
		return orderList;	
	}

	@GetMapping("/tracking")
	public TrackingBean trackOrder(@RequestParam int orderId) {
		TrackingBean trackingBean=orderService.track(orderId);
		return trackingBean;
	}

	//user Rating
	@PostMapping("/userRating")
	public String getRating(@RequestBody RatingRequestBean ratingRequestBean){
		return orderService.getRating(ratingRequestBean);

	}

}
