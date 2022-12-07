package com.kodlamaio.invoiceService.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateInvoiceResponse {
	private String id;
	 private String cardHolder;
	 private double totalPrice;

}
