package com.kodlamaio.paymentService.business.abstracts;

import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.RentalPaymentRequest;
import com.kodlamaio.paymentService.business.response.CreatePaymentResponse;

public interface PaymentService {
	
	CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest);
	void checkPayment(RentalPaymentRequest rentalPaymentRequest);
	
}
