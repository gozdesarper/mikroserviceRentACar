package com.kodlamaio.paymentService.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RentalPaymentRequest {
	
	 private String cardNo;
	 private String cardHolder;
	 private String cvv;
	 private String cardDate;
	 private double totalPrice;
	
}