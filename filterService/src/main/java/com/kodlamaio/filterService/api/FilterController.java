package com.kodlamaio.filterService.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.filterService.business.abstracs.FilterService;
import com.kodlamaio.filterService.business.response.GetAllFiltersResponse;
import com.kodlamaio.filterService.business.response.GetFiltersResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/filters")
@AllArgsConstructor
public class FilterController {
	private FilterService filterService;
	
	@GetMapping
	List<GetAllFiltersResponse> getAll(){
		return filterService.getAll();
	}
	@GetMapping("/{brandName}")
	List<GetAllFiltersResponse> getByBrandName(@PathVariable String brandName){
		return this.filterService.getByBrandName(brandName);
	}
	@GetMapping("/{modelName}")
	List<GetAllFiltersResponse> getByModelName(@PathVariable String modelName){
		return filterService.getByModelName(modelName);
		
	}
	@GetMapping("/{plate}")
	GetFiltersResponse getByPlate(@PathVariable String plate) {
		return filterService.getByPlate(plate);
	}
	@GetMapping("/dailyprice/{min}-{max}")
	List<GetAllFiltersResponse> getByDailyPrice(@PathVariable double min,@PathVariable double max){
		return filterService.getByDailyPrice(min, max);
		
	}
	@GetMapping("/modelyear/{min}-{max}")
	List<GetAllFiltersResponse> getByModelYear(@PathVariable int min, @PathVariable int max){
		return filterService.getByModelYear(min, max);
		
	}
	

}
