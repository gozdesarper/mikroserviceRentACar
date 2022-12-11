package com.kodlamaio.paymentService.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.RentalPaymentRequest;
import com.kodlamaio.paymentService.business.response.CreatePaymentResponse;
import com.kodlamaio.paymentService.business.response.GetAllPaymentResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {
	private PaymentService paymentService;
	
	@PostMapping
	public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
		return this.paymentService.add(createPaymentRequest);
	}
	@PostMapping("/checkpayment")
	public void checkPayment(@RequestParam String cardNo,@RequestParam String cardHolder,@RequestParam String cvv,@RequestParam String cardDate,@RequestParam double totalPrice) {
		RentalPaymentRequest paymentRequest = new RentalPaymentRequest(cardNo,cardHolder,cvv,cardDate,totalPrice);
		paymentService.checkPayment(paymentRequest);
	
	}
	@GetMapping()
	List<GetAllPaymentResponse> getAll(){
		return this.paymentService.getAll();
	}
	@DeleteMapping()
	void delete(String id) {
	this.paymentService.delete(id);
	}
	
 
}
