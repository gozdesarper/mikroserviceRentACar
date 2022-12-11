package com.kodlamaio.invoiceService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
    @Column(name = "id")
    private String id;
    @Column(name = "car_id")
    private String carId;
    @Column(name = "brandName")
    private String brandName;
    @Column(name = "modelName")
    private String modelName;
    @Column(name = "modelYear")
    private int modelYear;
    @Column(name = "cardHolder")
    private String cardHolder;
    @Column(name = "daily_price")
    private double dailyPrice;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "rented_for_days")
    private int rentedForDays;
}
