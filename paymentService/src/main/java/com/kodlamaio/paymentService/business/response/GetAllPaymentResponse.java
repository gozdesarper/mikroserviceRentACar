package com.kodlamaio.paymentService.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPaymentResponse {
	
	private String id;
	private String cardNo;
	private String cardHolder;
	private String cvv;
	private String cardDate;
	private double balance;

}
