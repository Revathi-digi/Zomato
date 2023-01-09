package com.freshersProject.ecommerce.responseBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingBean {
	
	private int orderId;
	private String restaurantName;
	private String itemName;
	private float orderTotal;
	private String orderStatus;

}
