package com.kodlamaio.inventoryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryServer.business.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateBrandResponse;

public interface BrandService {
	
	List<GetAllBrandsResponse> getAll();
	CreateBrandResponse add(CreateBrandRequest creatBrandRequest);
	UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest);
	void delete(String id);

}
