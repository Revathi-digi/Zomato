package com.freshersProject.ecommerce.responseBean;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersBean {
	
	private String restaurantName;
	
	private String itemName;
	
	private float orderTotal;
	
	private String orderStatus;
	
	private String orderPaymentStatus;
	
	private Date orderDate;
	
	private String paymentType; 
	
	private String userEmail;
	
}
