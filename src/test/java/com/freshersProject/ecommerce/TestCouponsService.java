package com.freshersProject.ecommerce;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.freshersProject.ecommerce.model.Coupons;
import com.freshersProject.ecommerce.repository.CouponsRepository;
import com.freshersProject.ecommerce.serviceImpl.CouponsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCouponsService {

	@InjectMocks
	private CouponsServiceImpl couponsServiceImpl;

	@Mock
	private CouponsRepository couponsRepository;
	
	@Test
	public void testGetAllCoupons() {
		Coupons couponsBean=new Coupons(1,"ZOMATO",100,"Above Rs 450");
		Coupons couponsBean1=new Coupons(2,"PAYNOW",150,"Above Rs 750");
		Coupons couponsBean2=new Coupons(3,"SUNDAY",200,"Above Rs 1000");
		List<Coupons> list=new ArrayList<Coupons>();
		list.add(couponsBean);
		list.add(couponsBean1);
		list.add(couponsBean2);
		when(couponsRepository.findAll()).thenReturn(list);	
		assertEquals(3, couponsServiceImpl.getAllCoupons().size());
		
	}

}
