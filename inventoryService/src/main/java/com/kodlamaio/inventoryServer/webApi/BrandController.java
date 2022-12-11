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

import com.kodlamaio.common.result.DataResult;
import com.kodlamaio.common.result.Result;
import com.kodlamaio.inventoryServer.business.abstracts.BrandService;
import com.kodlamaio.inventoryServer.business.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateBrandResponse;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandController {
	private BrandService brandService;
	
	@GetMapping("/getall")
	DataResult<List<GetAllBrandsResponse>> getAll(){
		 return this.brandService.getAll();
	}
	@PostMapping()
	DataResult<CreateBrandResponse> add(@Valid @RequestBody CreateBrandRequest creatBrandRequest) {
		return brandService.add(creatBrandRequest);
		
	}
	@PutMapping()
	DataResult<UpdateBrandResponse> update(@Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
		return brandService.update(updateBrandRequest);
	}
	@DeleteMapping("/{id}")
	Result delete(@PathVariable String id) {
		return brandService.delete(id);
	}

}