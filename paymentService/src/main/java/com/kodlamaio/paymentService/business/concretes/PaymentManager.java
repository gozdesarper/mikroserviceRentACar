package com.kodlamaio.paymentService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.RentalPaymentRequest;
import com.kodlamaio.paymentService.business.response.CreatePaymentResponse;
import com.kodlamaio.paymentService.business.response.GetAllPaymentResponse;
import com.kodlamaio.paymentService.dataAccess.PaymentRepository;
import com.kodlamaio.paymentService.entities.Payment;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService{
	private PaymentRepository paymentRepository;
	private ModelMapperService modelMapperService;

	
	@Override
	public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
		checkIfCardNo(createPaymentRequest.getCardNo());
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		this.paymentRepository.save(payment);	
		CreatePaymentResponse createPaymentResponse = this.modelMapperService.forResponse().map(payment, CreatePaymentResponse.class);
		return createPaymentResponse;
	}

	@Override
	public void checkPayment(RentalPaymentRequest rentalPaymentRequest) {
		if(!paymentRepository.existsByCardNoAndCardHolderAndCvvAndCardDate(
				rentalPaymentRequest.getCardNo(),
				rentalPaymentRequest.getCardHolder(),
				rentalPaymentRequest.getCvv(), 
				rentalPaymentRequest.getCardDate())) {			
			throw new BusinessException("NOT_A_VALİD_PAYMENT!");
		}
		else {
			Payment payment = paymentRepository.findByCardNo(rentalPaymentRequest.getCardNo());
			double balance = payment.getBalance();
			if(rentalPaymentRequest.getTotalPrice()>balance) {
				throw new BusinessException("NOT_ENOUGH_BALANCE");
			}
			else {
				payment.setBalance(balance-rentalPaymentRequest.getTotalPrice());
				this.paymentRepository.save(payment);
			}
			
		}
		
	}
			private void checkIfCardNo(String cardNo) {
			Payment payment = paymentRepository.findByCardNo(cardNo);
			if (payment!=null) {
				throw new BusinessException("card No exist");
			}
			}
		
			@Override
			public List<GetAllPaymentResponse> getAll() {
				List<Payment> payments = this.paymentRepository.findAll();
				List<GetAllPaymentResponse> responses = payments.stream().map(payment -> this.modelMapperService
						.forResponse().map(payment, GetAllPaymentResponse.class)).collect(Collectors.toList());
				return responses;
			}
		
			@Override
			public void delete(String id) {
				this.paymentRepository.deleteAll();
			}

}
