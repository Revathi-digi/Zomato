package com.freshersProject.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import com.freshersProject.ecommerce.model.Cart;
import com.freshersProject.ecommerce.model.Items;
import com.freshersProject.ecommerce.model.Orders;
import com.freshersProject.ecommerce.repository.CartRepository;
import com.freshersProject.ecommerce.repository.ItemsRepository;
import com.freshersProject.ecommerce.repository.OrdersRepository;
import com.freshersProject.ecommerce.requestBean.AddCartRequestBean;
import com.freshersProject.ecommerce.requestBean.PayRequestBean;
import com.freshersProject.ecommerce.responseBean.CartBean;
import com.freshersProject.ecommerce.responseBean.PayBean;
import com.freshersProject.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService {


	private CartRepository cartRepository;

	private ItemsRepository itemsRepository;

	private OrdersRepository ordersRepository;

	public CartServiceImpl(CartRepository cartRepository, ItemsRepository itemsRepository,
			OrdersRepository ordersRepository) {
		super();
		this.cartRepository = cartRepository;
		this.itemsRepository = itemsRepository;
		this.ordersRepository = ordersRepository;
	}
	
	private String restaurants="";

	@Override
	public CartBean addItems(AddCartRequestBean addCartRequestBean) {

		Cart cart=new Cart();
		CartBean cartBean=new CartBean();
		List<Cart> cartList=new ArrayList<>();
		cartList=cartRepository.findAll();
		Items items=new Items();
		items=itemsRepository.findByRestaurantNameAndItemName(addCartRequestBean.getRestaurantName().trim(),addCartRequestBean.getItemName().trim());

		float total=0;
		float discount;
		if(cartList.isEmpty()) {
			this.restaurants=addCartRequestBean.getRestaurantName();
			cart.setItemName(items.getItemName());
			cartBean.setItemName(items.getItemName());
			cart.setItemPrice(items.getItemPrice());
			cartBean.setItemPrice(items.getItemPrice());
			cart.setDiscount(items.getItemDiscount());
			cartBean.setDiscount(items.getItemDiscount());
			cart.setItemId(items.getItemId());
			cart.setRestaurantName(items.getRestaurantName());
			cartBean.setRestaurantName(items.getRestaurantName());
			cart.setUserEmail(addCartRequestBean.getUserEmail());
			cartBean.setUserEmail(addCartRequestBean.getUserEmail());
			discount=items.getItemDiscount();
			discount/=100;
			total=(items.getItemPrice())-(items.getItemPrice()*discount);
			cart.setItemPriceWithDiscount(total);
			cartBean.setItemPriceWithDiscount(total);
			this.cartRepository.save(cart);
			return cartBean;
		}

		else{

//			this.restauran = cartList.stream().map(c -> c.getRestaurantName()).collect(Collectors.toList());
//			if(!restauran.equals(restaurant))
//			String name=cartRepository.findByRestaurantName(restaurant);
			
			if(restaurants.equalsIgnoreCase(addCartRequestBean.getRestaurantName())) {
				this.restaurants=addCartRequestBean.getRestaurantName();
				cart.setItemName(items.getItemName());
				cartBean.setItemName(items.getItemName());
				cart.setItemPrice(items.getItemPrice());
				cartBean.setItemPrice(items.getItemPrice());
				cart.setDiscount(items.getItemDiscount());
				cartBean.setDiscount(items.getItemDiscount());
				cart.setItemId(items.getItemId());
				cart.setRestaurantName(items.getRestaurantName());
				cartBean.setRestaurantName(items.getRestaurantName());
				cart.setUserEmail(addCartRequestBean.getUserEmail());
				cartBean.setUserEmail(addCartRequestBean.getUserEmail());
				discount=items.getItemDiscount();
				discount/=100;
				total=(items.getItemPrice())-(items.getItemPrice()*discount);
				cart.setItemPriceWithDiscount(total);
				cartBean.setItemPriceWithDiscount(total);
				this.cartRepository.save(cart);
				return cartBean;
			}
			else {

				this.restaurants=addCartRequestBean.getRestaurantName();
				cartRepository.deleteAll();
				cart.setItemName(items.getItemName());
				cartBean.setItemName(items.getItemName());
				cart.setItemPrice(items.getItemPrice());
				cartBean.setItemPrice(items.getItemPrice());
				cart.setDiscount(items.getItemDiscount());
				cartBean.setDiscount(items.getItemDiscount());
				cart.setItemId(items.getItemId());
				cart.setRestaurantName(items.getRestaurantName());
				cartBean.setRestaurantName(items.getRestaurantName());
				cart.setUserEmail(addCartRequestBean.getUserEmail());
				cartBean.setUserEmail(addCartRequestBean.getUserEmail());
				discount=items.getItemDiscount();
				discount/=100;
				total=0;
				total=(items.getItemPrice())-(items.getItemPrice()*discount);
				cart.setItemPriceWithDiscount(total);
				cartBean.setItemPriceWithDiscount(total);
				this.cartRepository.save(cart);
				return cartBean;
			}
		}
	}

	@Override
	public List<CartBean> viewUserCart(String userEmail) {
		List<CartBean> cartListBean=new ArrayList<>();
		List<Cart> cartList=new ArrayList<>();
		cartRepository.findAllByUserEmail(userEmail).forEach(cart->cartList.add(cart));
		for(Cart cart:cartList) {
			CartBean bean=new CartBean();
			bean.setUserEmail(cart.getUserEmail());
			bean.setRestaurantName(cart.getRestaurantName());
			bean.setItemName(cart.getItemName());
			bean.setDiscount(cart.getDiscount());
			bean.setItemPrice(cart.getItemPrice());
			cartListBean.add(bean);
		}
		return cartListBean;
	}

	@Override
	public List<CartBean> viewCart() {
		List<CartBean> cartListBean=new ArrayList<>();
		List<Cart> cartList=new ArrayList<>();
		cartRepository.findAll().forEach(cart->cartList.add(cart));
		for(Cart cart:cartList) {
			CartBean cartBean=new CartBean();
			cartBean.setUserEmail(cart.getUserEmail());
			cartBean.setRestaurantName(cart.getRestaurantName());
			cartBean.setItemName(cart.getItemName());
			cartBean.setDiscount(cart.getDiscount());
			cartBean.setItemPrice(cart.getItemPrice());
			cartListBean.add(cartBean);
		}
		return cartListBean;
	}

	@Override
	public String deleteOne(AddCartRequestBean addCartRequestBean) {
		Cart cart=	cartRepository.findByUserEmailAndRestaurantNameAndItemName(addCartRequestBean.getUserEmail(),
				addCartRequestBean.getRestaurantName(),addCartRequestBean.getItemName());
		try {
			cartRepository.delete(cart);
			return "Successfully Deleted";
		}catch(Exception e){
			return "Enter Correct Details";
		}
		
	}

	@Override
	public String deleteCart() {
		try {
			cartRepository.deleteAll();
			return "Successfully Deleted";
		}catch(Exception e){
			return "Enter Correct Details";
		}
	}

	@Override
	public PayBean payment(PayRequestBean payRequestBean){
		PayBean payBean=new PayBean();
		List<Cart> cartList=new ArrayList<>();
		cartRepository.findAllByUserEmail(payRequestBean.getUserMail()).forEach(cart->cartList.add(cart));
		float totalPrice=0;
		String itemnames="";
		Cart cart= cartList.get(0);
		Orders orders=new Orders();
		for (Cart tempCart : cartList) {		
			itemnames=itemnames.concat(tempCart.getItemName()+",");
			orders.setItemName(itemnames);
		}
		for(Cart itemsTotal:cartList)
		{
			totalPrice+=itemsTotal.getItemPriceWithDiscount();
		}
       
		if(totalPrice>=450) {
			totalPrice-=100;
		}else if(totalPrice>=700) {
			totalPrice-=150;
		}
		else if(totalPrice>=10000) {
			totalPrice-=200;
		}
		
		orders.setUserEmail(cart.getUserEmail());
		orders.setRestaurantName(cart.getRestaurantName());
		orders.setOrderStatus("order accepted");
		if(payRequestBean.getPayType().equalsIgnoreCase("cash"))
			orders.setOrderPaymentStatus("cash");
		else
			orders.setOrderPaymentStatus("Paid");

		orders.setPaymentType(payRequestBean.getPayType());
		orders.setOrderDate(Calendar.getInstance().getTime());	
		orders.setOrderTotal(totalPrice);
		ordersRepository.save(orders);
		
		payBean.setOrderId(orders.getOrderId());
		payBean.setOutPut("Order Will be Deliver in next 40 Min");
		payBean.setTotal(totalPrice);
		return payBean;
	}

}
