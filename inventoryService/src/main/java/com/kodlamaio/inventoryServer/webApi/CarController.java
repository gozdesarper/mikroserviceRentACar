package com.kodlamaio.inventoryServer.webApi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.inventoryServer.business.abstracts.CarService;
import com.kodlamaio.inventoryServer.business.request.create.CreatCarRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateCarResponse;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
	private CarService carService;
	private 
	@GetMapping()
	List<GetAllCarsResponse> getAll(){
		return this.carService.getAll();
	}
	@PostMapping()
	CreateCarResponse add(@Valid @RequestBody CreatCarRequest creatcarRequest) {
		return carService.add(creatcarRequest);
	}
	@PutMapping()
	UpdateCarResponse update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
		return carService.update(updateCarRequest);
	}
	@DeleteMapping("{id}")
	void delete(@PathVariable String id) {
		carService.delete(id);
	}
	@GetMapping("/{carId}")
	public void checkIfByCarId(@PathVariable String carId) {
		 carService.checkIfCarAvailable(carId);
	}
}
