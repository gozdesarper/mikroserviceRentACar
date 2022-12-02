package com.kodlamaio.rentalService.webApi;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.dto.request.CreateRentalRequest;
import com.kodlamaio.rentalService.business.dto.request.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.dto.response.CreateRentalResponse;
import com.kodlamaio.rentalService.business.dto.response.UpdateRentalResponse;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalController {
	private RentalService rentalService;
	
	@PostMapping()
	CreateRentalResponse add(@Valid @RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);

	}
	@PutMapping()
	UpdateRentalResponse update(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
	 return	this.rentalService.update(updateRentalRequest);
	}
	
	
}
