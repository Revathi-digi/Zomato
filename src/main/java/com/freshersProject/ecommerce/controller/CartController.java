package com.freshersProject.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freshersProject.ecommerce.requestBean.AddCartRequestBean;
import com.freshersProject.ecommerce.requestBean.PayRequestBean;
import com.freshersProject.ecommerce.responseBean.CartBean;
import com.freshersProject.ecommerce.responseBean.PayBean;
import com.freshersProject.ecommerce.service.CartService;

@RestController
public class CartController {
	
	private CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	
	CartBean cart;
	
	@PostMapping("/addCart")
	public CartBean addCart(@RequestBody AddCartRequestBean addCartRequestBean) {
		cart=cartService.addItems(addCartRequestBean);
		return cart;
	}
	
	@GetMapping("/viewUserCart")
	public List<CartBean> userCart(@RequestParam String userEmail) {
		List<CartBean> cartList=new ArrayList<>();
		cartList=cartService.viewUserCart(userEmail);
		return cartList;
	}
	
	@GetMapping("/viewCart")
	public List<CartBean> viewCart() {
		List<CartBean> cartList=new ArrayList<>();
		cartList=cartService.viewCart();
		return cartList;
	}
	
	@DeleteMapping("/deleteOne")
	public String delete(@RequestBody AddCartRequestBean addCartRequestBean) {
		return cartService.deleteOne(addCartRequestBean);
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteCart() {
		return cartService.deleteCart();
	}
	
	@PostMapping("/pay")
	public PayBean pay(@RequestBody PayRequestBean payRequestBean) {
		PayBean payBean=cartService.payment(payRequestBean);
		return payBean;
	}

	
}
