package com.freshersProject.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RestaurantDetails")
public class RestaurantDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int restaurantId;

	@NotNull(message = "Restaurrent name can not be null")
	private String restaurantName;

	@NotNull(message = "This field can not be null")
	private int lunchAvlTables;

	@NotNull(message = "this field can not be null")
	private int dinnerAvlTables;

	@NotNull(message = "Restaurrent name can not be null")
	private String restaurantAddress;

	@NotNull(message = "Booking Prioce can not be null")
	private int perTableBookingPrice;

}
