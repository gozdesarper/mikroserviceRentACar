package com.kodlamaio.rentalService.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")

public class Rental {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "carId")
	private String carId;
	@Column(name = "dateStarted")
	private LocalDateTime datestarted = LocalDateTime.now(); // girdiğin anı alıyor.
	@Column(name = "rentedForDays")
	private int  rentedForDays;
	@Column(name = "dailyPrice")
	private double dailyPrice;
	@Column(name = "totalPrice")
	private double totalPrice;

}
