package com.kodlamaio.inventoryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.common.request.RentalInventoryResponse;
import com.kodlamaio.common.result.DataResult;
import com.kodlamaio.common.result.Result;
import com.kodlamaio.inventoryServer.business.request.create.CreatCarRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateCarResponse;

public interface CarService {
	
	DataResult<List<GetAllCarsResponse>> getAll();

	DataResult<CreateCarResponse> add(CreatCarRequest creatcarRequest);

	DataResult<UpdateCarResponse> update(UpdateCarRequest updateCarRequest);

	Result delete(String id);
	
	void checkIfCarAvailable(String carId); // araba müsaitmi değil mi 
	
	void updateCarState(String carId, int state); // müsaitse state 1 olsun.state değiştir.
	
	void getModelYear(String modelId);

	RentalInventoryResponse getCarResponse(String carId);
}