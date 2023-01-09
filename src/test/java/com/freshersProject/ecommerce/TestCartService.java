package com.freshersProject.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.freshersProject.ecommerce.model.Cart;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.repository.CartRepository;
import com.freshersProject.ecommerce.repository.ItemsRepository;
import com.freshersProject.ecommerce.repository.OrdersRepository;
import com.freshersProject.ecommerce.requestBean.AddCartRequestBean;
import com.freshersProject.ecommerce.requestBean.PayRequestBean;
import com.freshersProject.ecommerce.serviceImpl.CartServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCartService {
	
	@InjectMocks
	CartServiceImpl cartServiceImpl;
	
	@Mock
	CartRepository cartRepository;
	
	@Mock
	ItemsRepository itemsRepository;
	
	@Mock
	OrdersRepository ordersRepository;
	
	AddCartRequestBean addCartRequestBean;
	Cart cart;
	Cart carts;
	Items items;
	Orders orders;
	PayRequestBean payRequestBean;
	
	@BeforeEach
	public void setUp() {
		addCartRequestBean=new AddCartRequestBean("harish@gmail.com","Santhosh","Pizza");
		
		items=new Items();
		items.setItemId(1);
		items.setRestaurantName("Santhosh");
		items.setItemName("Pizza");
		items.setItemPrice(230);
		items.setItemType("snak");
		items.setItemDiscount(10);
		
		carts=new Cart();
		
		cart=new Cart();
		cart.setCartId(1);
		cart.setUserEmail("harish@gmail.com");
		cart.setRestaurantName("Santhosh");
		cart.setItemId(2);
		cart.setItemName("Pizza");
		cart.setItemPrice(230);
		cart.setItemPriceWithDiscount(210);
		cart.setDiscount(10);
		
		payRequestBean=new PayRequestBean("harish@gmail.com","card");
		
		orders=new Orders();
	}
	
	@Test
	public void InsertCartTest1() {	
		when(cartRepository.save(carts)).thenReturn(carts);
		when(itemsRepository.findByRestaurantNameAndItemName(addCartRequestBean.getRestaurantName(),addCartRequestBean.getItemName())).thenReturn(items);
		assertEquals(carts.getRestaurantName(), cartServiceImpl.addItems(addCartRequestBean).getRestaurantName());
	}
	
	@Test
	public void InsertCartTest() {	
		when(cartRepository.save(cart)).thenReturn(cart);
		when(itemsRepository.findByRestaurantNameAndItemName(addCartRequestBean.getRestaurantName(),addCartRequestBean.getItemName())).thenReturn(items);
		assertEquals(cart.getRestaurantName(), cartServiceImpl.addItems(addCartRequestBean).getRestaurantName());
	}
	
	@Test
	public void TestViewUserCart() {
		String userEmail="harish@gmail.com";
		when(cartRepository.findAllByUserEmail(userEmail)).thenReturn(Stream.of(cart).collect(Collectors.toList()));
		assertEquals(1,cartServiceImpl.viewUserCart(userEmail).size());
	}
	
	@Test
	public void TestViewCart() {
		when(cartRepository.findAll()).thenReturn(Stream.of(cart).collect(Collectors.toList()));
		assertEquals(1,cartServiceImpl.viewCart().size());
	}
	
	@Test
	public void TestDeleteOne() {
		when(cartRepository.findByUserEmailAndRestaurantNameAndItemName(addCartRequestBean.getUserEmail(),
				addCartRequestBean.getRestaurantName(),addCartRequestBean.getItemName())).thenReturn(cart);
		assertEquals("Successfully Deleted",cartServiceImpl.deleteOne(addCartRequestBean));
	}
	
	@Test
	public void TestDeleteCart() {
		assertEquals("Successfully Deleted",cartServiceImpl.deleteCart());
	}
	
	@Test
	public void TestPayment() {
		when(cartRepository.findAllByUserEmail(payRequestBean.getUserMail())).thenReturn(Stream.of(cart).collect(Collectors.toList()));
		when(ordersRepository.save(orders)).thenReturn(orders);
		assertEquals(orders.getOrderId(),cartServiceImpl.payment(payRequestBean).getOrderId());
	}
}
