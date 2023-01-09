package com.freshersProject.ecommerce.service;

import java.util.List;

import com.freshersProject.ecommerce.requestBean.AddCartRequestBean;
import com.freshersProject.ecommerce.requestBean.PayRequestBean;
import com.freshersProject.ecommerce.responseBean.CartBean;
import com.freshersProject.ecommerce.responseBean.PayBean;

public interface CartService {

	CartBean addItems(AddCartRequestBean addCartRequestBean);

	List<CartBean> viewUserCart(String userEmail);
	
	List<CartBean> viewCart();

	String deleteOne(AddCartRequestBean addCartRequestBean);

	String deleteCart();

	PayBean payment(PayRequestBean payRequestBean);

}
