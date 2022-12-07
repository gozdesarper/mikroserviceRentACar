package com.kodlamaio.inventoryServer.business.concretes;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filter.BrandUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryServer.business.abstracts.BrandService;
import com.kodlamaio.inventoryServer.business.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.inventoryServer.dataAccess.BrandRepository;
import com.kodlamaio.inventoryServer.entities.Brand;
import com.kodlamaio.inventoryServer.kafka.InventoryProducer;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BrandManager  implements BrandService{
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private InventoryProducer inventoryProducer;
	

	@Override
	public List<GetAllBrandsResponse> getAll() {
		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandsResponse> responses = 
			brands.stream().map(brand -> 
			this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
			.collect(Collectors.toList());
			return responses;
		}

	@Override
	public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());
		Brand result =	this.brandRepository.save(brand);
		
		CreateBrandResponse createBrandResponse = this.modelMapperService.forResponse().map(result, CreateBrandResponse.class);
		return createBrandResponse;
	}

	@Override
	public UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest) {
		checkIfExistBrandId (updateBrandRequest.getId()) ;
		checkIfBrandExistsByName(updateBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brand.setId(updateBrandRequest.getId());
		Brand result=	this.brandRepository.save(brand);
		
		BrandUpdatedEvent brandUpdatedEvent = this.modelMapperService.forRequest().map(result, BrandUpdatedEvent.class);
		brandUpdatedEvent.setBrandId(result.getId());
		brandUpdatedEvent.setBrandName(result.getName());
		brandUpdatedEvent.setMessage("Brand Updated");
		this.inventoryProducer.sendMessage(brandUpdatedEvent);
		UpdateBrandResponse response = this.modelMapperService.forResponse().map(result, UpdateBrandResponse.class);
		return response;
	}

	@Override
	public void delete(String id) {
	checkIfExistBrandId (id);
	brandRepository.deleteById(id);
		
	}
	private void checkIfExistBrandId (String id) {
		if(!brandRepository.existsById(id)) {
			throw new BusinessException("User not found");
		}
	}
	private void checkIfBrandExistsByName(String name) {
		Brand brand = this.brandRepository.findByNameIgnoreCase(name);
		if(brand!=null) {
		    throw new BusinessException("BRAND.EXISTS");
		}
	}
}

