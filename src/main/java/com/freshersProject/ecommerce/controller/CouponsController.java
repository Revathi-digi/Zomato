package com.freshersProject.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freshersProject.ecommerce.responseBean.CouponsBean;
import com.freshersProject.ecommerce.service.CouponsService;

@RestController
public class CouponsController {
	
	private CouponsService couponsService;
	
	public CouponsController(CouponsService couponsService) {
		this.couponsService = couponsService;
	}

	//user fetching coupons
		@GetMapping("/getCoupons")
		public List<CouponsBean> getAll(){
			List<CouponsBean> list=	new ArrayList<>();
			list=couponsService.getAllCoupons();
			return list;
		}
		
}
