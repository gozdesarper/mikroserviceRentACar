package com.kodlamaio.rentalService.business.abstracts;

import com.kodlamaio.rentalService.business.dto.request.CreateRentalRequest;
import com.kodlamaio.rentalService.business.dto.request.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.dto.response.CreateRentalResponse;
import com.kodlamaio.rentalService.business.dto.response.UpdateRentalResponse;

public interface RentalService {
	
	CreateRentalResponse add(CreateRentalRequest createRentalRequest);
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
	
}
