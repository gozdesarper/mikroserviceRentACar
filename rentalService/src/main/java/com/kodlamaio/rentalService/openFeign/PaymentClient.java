package com.kodlamaio.rentalService.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Headers;

@FeignClient(value = "paymentClient", url = "http://localhost:9011/") // gateway geç
public interface PaymentClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "payment/api/payments/checkpayment")
	@Headers(value = "Content-Type: application/json")
	void checkIfPaymentSuccessfull(@RequestParam String cardNo,@RequestParam String cardHolder,@RequestParam String cvv,@RequestParam String cardDate,@RequestParam double totalPrice); // sorumuz

}
