package com.kodlamaio.rentalService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentalService.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, String>{

}
