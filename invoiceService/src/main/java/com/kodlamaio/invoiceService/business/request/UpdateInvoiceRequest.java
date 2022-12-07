package com.kodlamaio.invoiceService.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	
	 private String cardHolder;
	 private double totalPrice;

	
}
