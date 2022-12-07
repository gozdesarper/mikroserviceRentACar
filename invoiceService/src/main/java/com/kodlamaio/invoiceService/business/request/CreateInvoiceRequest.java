package com.kodlamaio.invoiceService.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	 private String cardHolder;
	 private double totalPrice;

}
