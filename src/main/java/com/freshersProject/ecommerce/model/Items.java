package com.freshersProject.ecommerce.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="items")
public class Items {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int itemId;
	
	@Column(nullable=false)
	private String restaurantName;
	
	@Column(nullable=false)
	private String itemName;
	
	@Column(nullable=false)
	private float itemPrice;
	
	@Column(nullable=false)
	private String itemType;
	
	@Column(nullable=false)
	private int itemDiscount;
	
	
}
