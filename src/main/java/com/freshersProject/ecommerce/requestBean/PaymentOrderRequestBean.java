package com.freshersProject.ecommerce.requestBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentOrderRequestBean {
	
	private String userEmail;
	private String paymentType;

}
