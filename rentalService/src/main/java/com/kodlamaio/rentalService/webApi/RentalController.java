package com.kodlamaio.rentalService.webApi;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.dto.request.CreatePaymentRequest;
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
	CreateRentalResponse add(@Valid @RequestBody CreateRentalRequest createRentalRequest,@RequestParam String cardNo,@RequestParam String cardHolder,@RequestParam String cvv,@RequestParam String cardDate) {
		CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest(cardNo,cardHolder,cvv,cardDate);
		return this.rentalService.add(createRentalRequest,createPaymentRequest);

	}
	@PutMapping()
	UpdateRentalResponse update(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
	 return	this.rentalService.update(updateRentalRequest);
	}
	}

