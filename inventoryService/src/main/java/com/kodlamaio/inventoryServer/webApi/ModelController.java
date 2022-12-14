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
import com.kodlamaio.inventoryServer.business.abstracts.ModelService;
import com.kodlamaio.inventoryServer.business.request.create.CreatModelRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllModelResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateModelResponse;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/models")
@AllArgsConstructor

public class ModelController {
	private  ModelService modelService;
	
	@GetMapping("/getall")
	DataResult<List<GetAllModelResponse>> getAll(){
		return modelService.getAll();
	}
	@PostMapping("")
	DataResult<CreateModelResponse> add(@Valid @RequestBody CreatModelRequest creatModelRequest) {
		return modelService.add(creatModelRequest);
		
	}
	@PutMapping("")
	DataResult<UpdateModelResponse> update(@Valid @RequestBody UpdateModelRequest updateModelRequest) {
		return modelService.update(updateModelRequest);
	}
	@DeleteMapping("{id}")
	Result delete(@PathVariable String id) {
		return modelService.delete(id);
	}
	}
