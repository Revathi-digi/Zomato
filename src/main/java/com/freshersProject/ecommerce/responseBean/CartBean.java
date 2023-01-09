package com.freshersProject.ecommerce.responseBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartBean {

private String userEmail;
	
	private String restaurantName;
	
	private String itemName;
	
	private float itemPrice;
	
	private int discount;
	
	private float itemPriceWithDiscount;
	
	
}
