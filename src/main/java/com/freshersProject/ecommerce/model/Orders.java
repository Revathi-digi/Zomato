package com.freshersProject.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private int orderId;
	
	private String userEmail;

	@NotNull(message = "Name cannot be null")
	private String restaurantName;

	private String itemName;

	private float orderTotal;

	private String orderStatus;

	private String orderPaymentStatus;

	private String paymentType;
	
	@DateTimeFormat
	private Date orderDate;
	
	@Column(nullable=true)
	private float itemRating;

}
