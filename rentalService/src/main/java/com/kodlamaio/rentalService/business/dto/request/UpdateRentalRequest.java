package com.kodlamaio.rentalService.business.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	private String id;
	private String carId;
	private double dailyPrice;
	private int  rentedForDays;

}
