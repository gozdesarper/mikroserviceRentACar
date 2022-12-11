package com.kodlamaio.inventoryServer.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filter.CarCreatedEvent;
import com.kodlamaio.common.events.filter.CarDeletedEvent;
import com.kodlamaio.common.events.filter.CarUpdatedEvent;
import com.kodlamaio.common.request.RentalInventoryResponse;
import com.kodlamaio.common.result.DataResult;
import com.kodlamaio.common.result.Result;
import com.kodlamaio.common.result.SuccessDataResult;
import com.kodlamaio.common.result.SuccessResult;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryServer.business.abstracts.CarService;
import com.kodlamaio.inventoryServer.business.request.create.CreatCarRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryServer.dataAccess.CarRepository;
import com.kodlamaio.inventoryServer.entities.Car;
import com.kodlamaio.inventoryServer.kafka.InventoryProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private InventoryProducer inventoryProducer;

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> responses = cars.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllCarsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCarsResponse>>(responses,"Cars Listed");
	}
	
	@Override
	public DataResult<CreateCarResponse> add(CreatCarRequest creatcarRequest) {		
		checkIfExistCarPlate(creatcarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(creatcarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		Car result = this.carRepository.save(car);
		
		CarCreatedEvent carCreatedEvent = this.modelMapperService.forRequest().map(result, CarCreatedEvent.class);
		carCreatedEvent.setBrandId(result.getModel().getBrand().getId());
		carCreatedEvent.setBrandName(result.getModel().getBrand().getName());
		carCreatedEvent.setCarId(result.getId());
		carCreatedEvent.setModelId(result.getModel().getId());
		carCreatedEvent.setModelName(result.getModel().getId());
		carCreatedEvent.setModelYear(result.getModelYear());
		carCreatedEvent.setMessage("Car Created");
		this.inventoryProducer.sendMessage(carCreatedEvent);
		CreateCarResponse createCarResponse = this.modelMapperService.forResponse().map(result,CreateCarResponse.class);
		return new SuccessDataResult<CreateCarResponse>(createCarResponse,"Car Created");
		

	}

	@Override
	public DataResult<UpdateCarResponse> update(UpdateCarRequest updateCarRequest) {
		checkIfExistCarId(updateCarRequest.getId());
		checkIfExistCarPlate(updateCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		Car result = this.carRepository.save(car);
		CarUpdatedEvent carUpdatedEvent = this.modelMapperService.forRequest().map(result, CarUpdatedEvent.class);
		carUpdatedEvent.setBrandId(result.getModel().getBrand().getId());
		carUpdatedEvent.setBrandName(result.getModel().getBrand().getName());
		carUpdatedEvent.setCarId(result.getId());
		carUpdatedEvent.setModelId(result.getModel().getId());
		carUpdatedEvent.setModelName(result.getModel().getId());
		carUpdatedEvent.setMessage("Car Updated");
		
		this.inventoryProducer.sendMessage(carUpdatedEvent);
		UpdateCarResponse updateCarResponse = this.modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return new SuccessDataResult<UpdateCarResponse>(updateCarResponse,"Car Updated");
	}

	@Override
	public Result delete(String id) {
		checkIfExistCarId(id);
		this.carRepository.deleteById(id);
		
		CarDeletedEvent carDeletedEvent  = new CarDeletedEvent() ;
		carDeletedEvent.setCarId(id);
		carDeletedEvent.setMessage("car deleted");
		this.inventoryProducer.sendMessage(carDeletedEvent);
		return new SuccessResult("Car Deleted");
	}

	private void checkIfExistCarId(String id) {
		if (!carRepository.existsById(id)) {
			throw new BusinessException("User not found");
		}
	}

	private void checkIfExistCarPlate(String plate) {
		Car car = carRepository.findByPlate(plate);
		if (car != null) {
			throw new BusinessException("Car Plate Exist");
		}
	}

	@Override
	public void updateCarState(String carId, int state) { // kıralan carId nini state durumunu güncellliyoruz.
		Car car = this.carRepository.findById(carId).get(); // optional durumda bu ıd null da dönebilir dönmeyedebilir.
		car.setState(state);
		carRepository.save(car);
	}
	
	@Override
	public void checkIfCarAvailable(String carId) {
		Car car = this.carRepository.findById(carId).orElse(null);
		if (car.getState() != 1 || car==null) {
			throw new BusinessException("carId not avalibale");
		}

	}

	@Override
	public void getModelYear(String modelId) {
		Car car = this.carRepository.findById(modelId).get();
		car.getModelYear();
		
	}

	@Override
	public RentalInventoryResponse getCarResponse(String carId) {
		Car car = this.carRepository.findCarById(carId);
		RentalInventoryResponse inventoryResponse = new RentalInventoryResponse();
		inventoryResponse.setBrandName(car.getModel().getBrand().getName());
		inventoryResponse.setModelName(car.getModel().getName());
		inventoryResponse.setModelYear(car.getModelYear());
		return inventoryResponse;
	}

}
