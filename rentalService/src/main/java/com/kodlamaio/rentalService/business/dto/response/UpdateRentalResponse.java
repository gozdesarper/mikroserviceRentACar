package com.kodlamaio.rentalService.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 

public class UpdateRentalResponse {
	private String id;
	private String carId;
	private double dailyPrice;
	private int  rentedForDays;
}
