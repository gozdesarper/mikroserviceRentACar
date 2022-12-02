package com.kodlamaio.inventoryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryServer.business.request.create.CreatCarRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryServer.business.responses.GetCarResponse;
import com.kodlamaio.inventoryServer.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateCarResponse;

public interface CarService {
	List<GetAllCarsResponse> getAll();

	CreateCarResponse add(CreatCarRequest creatcarRequest);

	UpdateCarResponse update(UpdateCarRequest updateCarRequest);

	void checkIfByCarId(String carId);

	void delete(String id);

	void updateCarState(String carId, int state);

}