package com.kodlamaio.filterService.dataAccess;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.filterService.entities.Filter;

public interface FilterRepository extends MongoRepository<Filter, String> {
	
	List<Filter> findByBrandName(String brandName);
	List<Filter> findByModelNameIgnoreCase(String modelName);
	Filter findByPlate(String plate);
	List<Filter> findByDailyPrice(String DailyPrice);
	List<Filter> findByModelYear(String ModelYear);
	

}


