package com.freshersProject.ecommerce.serviceImpl;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.repository.OrdersRepository;
import com.freshersProject.ecommerce.requestBean.OrderRequestBean;
import com.freshersProject.ecommerce.requestBean.OrderStatusRequestBean;
import com.freshersProject.ecommerce.requestBean.PaymentOrderRequestBean;
import com.freshersProject.ecommerce.requestBean.RatingRequestBean;
import com.freshersProject.ecommerce.responseBean.OrdersBean;
import com.freshersProject.ecommerce.responseBean.TrackingBean;
import com.freshersProject.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	private OrdersRepository orderRepository;

	public OrderServiceImpl(OrdersRepository orderHistoryRepository) {
		this.orderRepository = orderHistoryRepository;
	}


	//sort by purchase date ASC
	@Override
	public List<OrdersBean> getOrderHistoryDateByAsc(OrderRequestBean orderRequestBean){
		List<OrdersBean> orderList=new ArrayList<OrdersBean>();
		List<Orders>list=new ArrayList<>();
		orderRepository.findAllByuserEmail(orderRequestBean.getUserEmail()).forEach(order->list.add(order));
		for(Orders orders:list){
			OrdersBean ordersBean=new OrdersBean();
			ordersBean.setRestaurantName(orders.getRestaurantName());
			ordersBean.setUserEmail(orders.getUserEmail()); 
			ordersBean.setItemName(orders.getItemName());
			ordersBean.setOrderTotal(orders.getOrderTotal());
			ordersBean.setOrderStatus(orders.getOrderStatus());
			ordersBean.setOrderPaymentStatus(orders.getOrderPaymentStatus());
			ordersBean.setOrderDate(orders.getOrderDate()); 
			ordersBean.setPaymentType(orders.getPaymentType());
			orderList.add(ordersBean);
		}
		orderList.sort(Comparator.comparing(OrdersBean::getOrderDate));
		return orderList;	
	}

	//sort by purchase date DSC 
	@Override
	public List<OrdersBean> getOrderHistoryDateByDsc(OrderRequestBean orderRequestBean){
		List<OrdersBean> orderList=new ArrayList<OrdersBean>();
		List<Orders>list=new ArrayList<>();
		orderRepository.findAllByuserEmail(orderRequestBean.getUserEmail()).forEach(order->list.add(order));
		for(Orders orders:list){
			OrdersBean ordersBean=new OrdersBean();
			ordersBean.setRestaurantName(orders.getRestaurantName());
			ordersBean.setUserEmail(orders.getUserEmail()); 
			ordersBean.setItemName(orders.getItemName());
			ordersBean.setOrderTotal(orders.getOrderTotal());
			ordersBean.setOrderStatus(orders.getOrderStatus());
			ordersBean.setOrderPaymentStatus(orders.getOrderPaymentStatus());
			ordersBean.setOrderDate(orders.getOrderDate()); 
			ordersBean.setPaymentType(orders.getPaymentType());
			orderList.add(ordersBean);
		}
		orderList.sort(Comparator.comparing(OrdersBean::getOrderDate).reversed());
		return orderList;	
	}

	//user viewing his order history
	@Override
	public List<OrdersBean> getOderHistory(OrderRequestBean orderRequestBean) {
		List<OrdersBean> orderList=new ArrayList<OrdersBean>();
		List<Orders>list=new ArrayList<>();
		orderRepository.findAllByuserEmail(orderRequestBean.getUserEmail()).forEach(order->list.add(order));
		for(Orders orders:list){
			OrdersBean orderBean=new OrdersBean();
			orderBean.setRestaurantName(orders.getRestaurantName());
			orderBean.setUserEmail(orders.getUserEmail()); 
			orderBean.setItemName(orders.getItemName());
			orderBean.setOrderTotal(orders.getOrderTotal());
			orderBean.setOrderStatus(orders.getOrderStatus());
			orderBean.setOrderPaymentStatus(orders.getOrderPaymentStatus());
			orderBean.setOrderDate(orders.getOrderDate()); 
			orderBean.setPaymentType(orders.getPaymentType());
			orderList.add(orderBean); 
		} 
		return orderList; 
	}

	//User filtering by paymentType
	@Override
	public List<OrdersBean> getOrderHistoryByPaymentType(PaymentOrderRequestBean paymentOrderRequestBean) {
		List<OrdersBean> orderList=new ArrayList<OrdersBean>();
		List<Orders>list=orderRepository.findAllByuserEmail(paymentOrderRequestBean.getUserEmail()).stream().
				filter(x->x.getPaymentType().equalsIgnoreCase(paymentOrderRequestBean.getPaymentType())).toList();
		for(Orders orders:list){
			OrdersBean ordersBean=new OrdersBean();
			ordersBean.setRestaurantName(orders.getRestaurantName());
			ordersBean.setUserEmail(orders.getUserEmail()); 
			ordersBean.setItemName(orders.getItemName());
			ordersBean.setOrderTotal(orders.getOrderTotal());
			ordersBean.setOrderStatus(orders.getOrderStatus());
			ordersBean.setOrderPaymentStatus(orders.getOrderPaymentStatus());
			ordersBean.setOrderDate(orders.getOrderDate()); 
			ordersBean.setPaymentType(orders.getPaymentType());
			orderList.add(ordersBean); 
		} 
		return orderList; 
	}

	//User filtering by orderStatus
	@Override
	public List<OrdersBean> getOrderHistoryByOrderStatus(OrderStatusRequestBean  orderStatusRequestBean) {
		List<OrdersBean> orderList=new ArrayList<OrdersBean>();
		List<Orders>list=orderRepository.findAllByuserEmail(orderStatusRequestBean.getUserEmail()).stream().
				filter(x->x.getOrderStatus().equalsIgnoreCase(orderStatusRequestBean.getOrderStatus())).toList();
		for(Orders orders:list){
			OrdersBean ordersBean=new OrdersBean();
			ordersBean.setRestaurantName(orders.getRestaurantName());
			ordersBean.setUserEmail(orders.getUserEmail()); 
			ordersBean.setItemName(orders.getItemName());
			ordersBean.setOrderTotal(orders.getOrderTotal());
			ordersBean.setOrderStatus(orders.getOrderStatus());
			ordersBean.setOrderPaymentStatus(orders.getOrderPaymentStatus());
			ordersBean.setOrderDate(orders.getOrderDate()); 
			ordersBean.setPaymentType(orders.getPaymentType());
			orderList.add(ordersBean); 
		} 
		return orderList; 
	}

	@Override
	public TrackingBean track(int orderId) {
		TrackingBean trackingBean = new TrackingBean();
		Orders orders= orderRepository.findByOrderId(orderId);
		trackingBean.setOrderId(orders.getOrderId());
		trackingBean.setRestaurantName(orders.getRestaurantName());
		trackingBean.setItemName(orders.getItemName());
		trackingBean.setOrderTotal(orders.getOrderTotal());
		trackingBean.setOrderStatus(orders.getOrderStatus());
		return trackingBean;
	}

	@Override
	public String getRating(RatingRequestBean ratingRequestBean) {
		Orders orders=new Orders();
		try {
		orders=orderRepository.findByUserEmailAndRestaurantName(ratingRequestBean.getUserEmail(),ratingRequestBean.getRestaurantName());		
		orders.setItemRating(ratingRequestBean.getItemRating());
		orderRepository.save(orders);
		return "Thankyou for Rating";
		}catch(Exception e) {
			return "Enter Corect Details";
		}
	}
}

