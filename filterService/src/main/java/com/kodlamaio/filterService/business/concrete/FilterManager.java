package com.kodlamaio.filterService.business.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filter.BrandUpdatedEvent;
import com.kodlamaio.common.events.filter.CarCreatedEvent;
import com.kodlamaio.common.events.filter.CarDeletedEvent;
import com.kodlamaio.common.events.filter.CarUpdatedEvent;
import com.kodlamaio.common.events.filter.ModelUpdatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterService.business.abstracs.FilterService;
import com.kodlamaio.filterService.business.response.GetAllFiltersResponse;
import com.kodlamaio.filterService.business.response.GetFiltersResponse;
import com.kodlamaio.filterService.dataAccess.FilterRepository;
import com.kodlamaio.filterService.entities.Filter;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class FilterManager implements FilterService{
	private FilterRepository filterRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllFiltersResponse> getAll() {
		List<Filter> filters = filterRepository.findAll();
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(filter -> this.modelMapperService.forRequest().map(filter, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByBrandName(String brandName) {
		List<Filter> filters = filterRepository.findByBrandName(brandName);
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(filter -> this.modelMapperService.forRequest().map(filter, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByModelName(String modelName) {
		List<Filter> filters = filterRepository.findByModelNameIgnoreCase(modelName);
		List<GetAllFiltersResponse> responses = filters.stream()
				.map(filter -> this.modelMapperService.forRequest().map(filter, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public GetFiltersResponse getByPlate(String plate) {
		Filter filter = filterRepository.findByPlate(plate);
		GetFiltersResponse getFiltersResponse = this.modelMapperService.forResponse().map(filter, GetFiltersResponse.class);
		return getFiltersResponse;
	}

	@Override
	public List<GetAllFiltersResponse> getByDailyPrice(double min, double max) {
		List<Filter> filters = this.filterRepository.findAll();
		List<GetAllFiltersResponse> responses = new ArrayList<GetAllFiltersResponse>();
		for (Filter filter : filters) {
			if(filter.getDailyPrice()<max && filter.getDailyPrice()>min) {
				GetAllFiltersResponse response = this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class);
				responses.add(response);
		
			}
			
		}
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByModelYear(int min, int max) {
		List<Filter> filters = this.filterRepository.findAll();
		List<GetAllFiltersResponse> responses = new ArrayList<GetAllFiltersResponse>();
		for (Filter filter : filters) {
			if(filter.getModelYear()<max && filter.getModelYear()>min) {
				GetAllFiltersResponse response = this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class);
				responses.add(response);
		
			}
			
		}
		return responses;
	}

	@Override
	public void addCar(CarCreatedEvent carCreatedEvent) {
	Filter filter = this.modelMapperService.forRequest().map(carCreatedEvent, Filter.class);
		this.filterRepository.save(filter);
	}

	@Override
	public void deleteCar(CarDeletedEvent carDeletedEvent) {
		this.filterRepository.deleteAll();
	}

	@Override
	public void updateCar(CarUpdatedEvent carUpdatedEvent) {
		Filter filter = this.modelMapperService.forRequest().map(carUpdatedEvent, Filter.class);
		this.filterRepository.save(filter);
		
	}

	@Override
	public void updateBrand(BrandUpdatedEvent brandUpdatedEvent) {
	Filter filter = this.modelMapperService.forRequest().map(brandUpdatedEvent, Filter.class);
		this.filterRepository.save(filter);
		
	}

	@Override
	public void updateModel(ModelUpdatedEvent modelUpdatedEvent) {
		Filter filter = this.modelMapperService.forRequest().map(modelUpdatedEvent, Filter.class);
		this.filterRepository.save(filter);
		
	}

}
