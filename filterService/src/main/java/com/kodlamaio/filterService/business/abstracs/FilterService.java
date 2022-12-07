package com.kodlamaio.filterService.business.abstracs;

import java.util.List;

import com.kodlamaio.common.events.filter.BrandUpdatedEvent;
import com.kodlamaio.common.events.filter.CarCreatedEvent;
import com.kodlamaio.common.events.filter.CarDeletedEvent;
import com.kodlamaio.common.events.filter.CarUpdatedEvent;
import com.kodlamaio.common.events.filter.ModelUpdatedEvent;
import com.kodlamaio.filterService.business.response.GetAllFiltersResponse;
import com.kodlamaio.filterService.business.response.GetFiltersResponse;

public interface FilterService {
	
	List<GetAllFiltersResponse> getAll();
	List<GetAllFiltersResponse> getByBrandName(String brandName);
	List<GetAllFiltersResponse> getByModelName(String modelName);
	GetFiltersResponse getByPlate(String plate);
	List<GetAllFiltersResponse> getByDailyPrice(double min,double max);
	List<GetAllFiltersResponse> getByModelYear(int min, int max);
	
	void addCar(CarCreatedEvent carCreatedEvent);
	void deleteCar(CarDeletedEvent carDeletedEvent);
	void updateCar(CarUpdatedEvent carUpdatedEvent);
	
	void updateBrand(BrandUpdatedEvent brandUpdatedEvent);
	void updateModel(ModelUpdatedEvent modelUpdatedEvent);

}
