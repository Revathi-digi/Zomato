package com.freshersProject.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.freshersProject.ecommerce.model.Coupons;
import com.freshersProject.ecommerce.repository.CouponsRepository;
import com.freshersProject.ecommerce.responseBean.CouponsBean;
import com.freshersProject.ecommerce.service.CouponsService;

@Service
public class CouponsServiceImpl implements CouponsService {

	private CouponsRepository couponsRepository;


	public CouponsServiceImpl(CouponsRepository couponsRepository) {
		this.couponsRepository = couponsRepository;
	}

	//fetching coupons
	@Override
	public List<CouponsBean> getAllCoupons() {
		List<CouponsBean> CouponList= new ArrayList<>();
		List<Coupons> list=new ArrayList<>();
		couponsRepository.findAll().forEach(coupons->list.add(coupons));
		for(Coupons coupons:list) {
			CouponsBean couponsBean=new CouponsBean();
			couponsBean.setCouponName(coupons.getCouponName());
			couponsBean.setCouponAmount(coupons.getCouponAmount());
			couponsBean.setBillReduce(coupons.getBillReduce());
			CouponList.add(couponsBean);
		}
		return CouponList;
	}
}

