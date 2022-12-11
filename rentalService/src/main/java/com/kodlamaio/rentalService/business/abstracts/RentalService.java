package com.kodlamaio.rentalService.business.abstracts;

import java.util.List;

import com.kodlamaio.rentalService.business.dto.request.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.dto.request.CreateRentalRequest;
import com.kodlamaio.rentalService.business.dto.request.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.dto.response.CreateRentalResponse;
import com.kodlamaio.rentalService.business.dto.response.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.dto.response.UpdateRentalResponse;

public interface RentalService {
	
	CreateRentalResponse add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest);
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
	List<GetAllRentalResponse> getAll();
	void delete(String id);
	
	
}
