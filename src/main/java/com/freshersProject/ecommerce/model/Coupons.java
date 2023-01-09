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
@Table(name="coupons")
public class Coupons {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private int couponId;
	
	@Column(length=40,nullable=false)
	private String couponName;
	
	@Column(length=10,nullable=false)
	private int couponAmount;
	

	@Column(length=100,nullable=false)
	private String billReduce;

}
