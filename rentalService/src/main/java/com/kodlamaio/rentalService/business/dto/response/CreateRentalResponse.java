package com.kodlamaio.rentalService.business.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateRentalResponse {
	
	private String id;
	private String carId;
	private LocalDateTime datestarted;
	private int rentedForDays;
	private double dailyPrice;
	private double totalPrice;

}
