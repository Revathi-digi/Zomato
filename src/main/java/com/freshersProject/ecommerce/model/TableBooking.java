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
@Table
public class TableBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingNumber")
	private int bookingNumber;
	@NotNull(message = "User name can not be null")
	private String userName;
	@NotNull(message = "Restaurrent name can not be null")
	private String restaurantName;
	@NotNull(message = "Food timing is necessary Lunch or Dinner")
	private String lunchOrdinner;
	@NotNull(message = "Table numbers can not be null")
	private int numberOfTables;
	@NotNull(message = "contact number can not be null")
	private String userPhoneNumber;
	private int numberOfSeats;
	private int amountPaid;
	private float restaurantRating;

}
