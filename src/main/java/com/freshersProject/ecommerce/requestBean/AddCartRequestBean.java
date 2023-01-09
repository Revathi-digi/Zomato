package com.freshersProject.ecommerce.requestBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCartRequestBean {

	private String userEmail;

	private String restaurantName;

	private String itemName;

}
