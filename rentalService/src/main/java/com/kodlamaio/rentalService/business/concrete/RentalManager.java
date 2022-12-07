package com.kodlamaio.rentalService.business.concrete;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.invoice.PaymentReceivedEvent;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.events.rental.RentalUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.dto.request.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.dto.request.CreateRentalRequest;
import com.kodlamaio.rentalService.business.dto.request.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.dto.response.CreateRentalResponse;
import com.kodlamaio.rentalService.business.dto.response.UpdateRentalResponse;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entity.Rental;
import com.kodlamaio.rentalService.kafka.RentalProducer;
import com.kodlamaio.rentalService.openFeign.LocalServiceClientCar;
import com.kodlamaio.rentalService.openFeign.PaymentClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService{
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalProducer rentalProducer;
	private LocalServiceClientCar localServiceClient;
	private PaymentClient paymentClient;
	

	
	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest) {
		this.localServiceClient.checkIfCarAvailable(createRentalRequest.getCarId());
	
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		rental.setTotalPrice(createRentalRequest.getDailyPrice()*createRentalRequest.getRentedForDays());
		
		
		this.paymentClient.checkIfPaymentSuccessfull(createPaymentRequest.getCardNo(),
				createPaymentRequest.getCardHolder(),
				createPaymentRequest.getCvv(), 
				createPaymentRequest.getCardDate(),
				rental.getTotalPrice() );
		rentalRepository.save(rental);
		
		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(rental.getCarId());
		rentalCreatedEvent.setMessage("Rental Created");
		this.rentalProducer.sendMessage(rentalCreatedEvent);
		
		//invoice
		PaymentReceivedEvent paymentReceivedEvent = new PaymentReceivedEvent();
		paymentReceivedEvent.setCardHolder(createPaymentRequest.getCardHolder());
		paymentReceivedEvent.setTotalPrice(rental.getTotalPrice());
		this.rentalProducer.sendMessage(paymentReceivedEvent);
		
		CreateRentalResponse createRentalResponse = this.modelMapperService.forResponse().map(rental, CreateRentalResponse.class);
		return createRentalResponse;
	}
	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
		checkIfExistByRentalId(updateRentalRequest.getId());
		this.localServiceClient.checkIfCarAvailable(updateRentalRequest.getCarId());
		
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
