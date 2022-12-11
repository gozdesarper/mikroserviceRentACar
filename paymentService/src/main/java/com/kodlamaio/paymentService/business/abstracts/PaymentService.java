package com.kodlamaio.paymentService.business.abstracts;

import java.util.List;

import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.RentalPaymentRequest;
import com.kodlamaio.paymentService.business.response.CreatePaymentResponse;
import com.kodlamaio.paymentService.business.response.GetAllPaymentResponse;

public interface PaymentService {
	
	CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest);
	void checkPayment(RentalPaymentRequest rentalPaymentRequest);
	List<GetAllPaymentResponse> getAll();
	void delete(String id);
}
