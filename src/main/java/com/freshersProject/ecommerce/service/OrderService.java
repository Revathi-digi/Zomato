package com.freshersProject.ecommerce.service;

import java.util.List;

import com.freshersProject.ecommerce.requestBean.OrderRequestBean;
import com.freshersProject.ecommerce.requestBean.OrderStatusRequestBean;
import com.freshersProject.ecommerce.requestBean.PaymentOrderRequestBean;
import com.freshersProject.ecommerce.requestBean.RatingRequestBean;
import com.freshersProject.ecommerce.responseBean.OrdersBean;
import com.freshersProject.ecommerce.responseBean.TrackingBean;

public interface OrderService {

	List<OrdersBean> getOderHistory(OrderRequestBean orderRequestBean);
	List<OrdersBean> getOrderHistoryDateByAsc(OrderRequestBean orderRequestBean);
	List<OrdersBean> getOrderHistoryDateByDsc(OrderRequestBean orderRequestBean);
	List<OrdersBean> getOrderHistoryByOrderStatus(OrderStatusRequestBean  orderStatusRequestBean);
	List<OrdersBean> getOrderHistoryByPaymentType(PaymentOrderRequestBean paymentOrderRequestBean);
	TrackingBean track(int orderId);
	String getRating(RatingRequestBean ratingRequestBean);

}
