package com.kodlamaio.common.events.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentReceivedEvent {
	
	
	private String cardHolder;
	private double totalPrice;
	private String carId;
	private int  rentedForDays;
	private double dailyPrice;
	private String modelName;
	private String brandName;
	private int modelYear;
	
	


}