package com.kodlamaio.inventoryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.common.result.DataResult;
import com.kodlamaio.common.result.Result;
import com.kodlamaio.inventoryServer.business.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateBrandResponse;

public interface BrandService {
	
	DataResult<List<GetAllBrandsResponse>> getAll();
	DataResult<CreateBrandResponse> add(CreateBrandRequest creatBrandRequest);
	DataResult<UpdateBrandResponse> update(UpdateBrandRequest updateBrandRequest);
	Result delete(String id);
	Result getBrandName(String brandId);

}
