package com.kodlamaio.paymentService.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payments")
public class Payment {
	 @Id
	 @Column(name = "id")
	 private String id;
	 @Column(name = "cardNo")
	 private String cardNo;
	 @Column(name = "cardHolder")
	 private String cardHolder;
	 @Column(name = "cvv")
	 private String cvv;
	 @Column(name = "cardDate")
	 private String cardDate;
	 @Column(name = "balance") //kart limiti
	 private double balance;

	 
	 
	 
		
}
