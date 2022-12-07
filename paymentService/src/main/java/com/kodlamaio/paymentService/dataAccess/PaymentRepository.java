package com.kodlamaio.paymentService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.paymentService.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String>{
	boolean existsByCardNoAndCardHolderAndCvvAndCardDate(String cardNo,String cardHolder,String cvv,String cardDate);
	Payment findByCardNo(String cardNo);
	
}
