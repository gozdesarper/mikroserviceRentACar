package com.kodlamaio.rentalService.business.concrete;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryServer.business.abstracts.CarService;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.dto.request.CreateRentalRequest;
import com.kodlamaio.rentalService.business.dto.request.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.dto.response.CreateRentalResponse;
import com.kodlamaio.rentalService.business.dto.response.UpdateRentalResponse;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entity.Rental;
import com.kodlamaio.rentalService.kafka.RentalProducer;
import com.kodlamaio.rentalService.openFeign.LocalServiceClient;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor

public class RentalManager implements RentalService{
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalProducer rentalProducer;
	private LocalServiceClient localServiceClient;

	
	
	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
		this.localServiceClient.checkIfByCarId(createRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		rental.setTotalPrice(createRentalRequest.getDailyPrice()*createRentalRequest.getRentedForDays());
		rentalRepository.save(rental);
		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(rental.getCarId());
		rentalCreatedEvent.setMessage("Rental Created");
		this.rentalProducer.sendMessage(rentalCreatedEvent);
		CreateRentalResponse createRentalResponse = this.modelMapperService.forResponse().map(rental, CreateRentalResponse.class);
		return createRentalResponse;
	}
	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
		checkIfExistByRentalId(updateRentalRequest.getId());
		this.localServiceClient.checkIfByCarId(updateRentalRequest.getCarId());
		RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();
		rentalUpdatedEvent.setNewCarId(updateRentalRequest.getCarId());
		Rental result=this.rentalRepository.findById(updateRentalRequest.getId()).get();
		rentalUpdatedEvent.setOldCarId(result.getCarId());
		rentalUpdatedEvent.setMessage("Rental updated");
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setTotalPrice(updateRentalRequest.getDailyPrice()*updateRentalRequest.getRentedForDays());
		
		
		rentalRepository.save(rental);
		this.rentalProducer.sendMessage(rentalUpdatedEvent);
		UpdateRentalResponse updateRentalResponse = this.modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);
		return updateRentalResponse;
	}
	
	
	private void checkIfExistByRentalId(String id) {
		Rental rental = rentalRepository.findById(id).get();
		if(rental==null) {
			throw new BusinessException("id Not found");
		}
		
	}

}
