package com.kodlamaio.rentalService.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

@FeignClient(value = "localServiceClient", url = "http://localhost:9011/") // gatway gec
public interface LocalServiceClientCar {

	@RequestMapping(method = RequestMethod.GET, value = "stock/api/cars/{carId}") // inventorye git araba var mı
	@Headers(value = "Content-Type: application/json")
	void checkIfCarAvailable(@PathVariable String carId); // sorumuz

	}

