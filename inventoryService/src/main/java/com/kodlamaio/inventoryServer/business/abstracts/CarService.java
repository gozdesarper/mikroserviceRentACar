package com.kodlamaio.inventoryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryServer.business.request.create.CreatCarRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateCarResponse;

public interface CarService {
	List<GetAllCarsResponse> getAll();

	CreateCarResponse add(CreatCarRequest creatcarRequest);

	UpdateCarResponse update(UpdateCarRequest updateCarRequest);

	void delete(String id);
	
	void checkIfCarAvailable(String carId); // araba müsaitmi değil mi 
	
	void updateCarState(String carId, int state); // müsaitse state 1 olsun.state değiştir.


}