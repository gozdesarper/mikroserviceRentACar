package com.kodlamaio.inventoryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.common.result.DataResult;
import com.kodlamaio.common.result.Result;
import com.kodlamaio.inventoryServer.business.request.create.CreatModelRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllModelResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateModelResponse;

public interface ModelService {
	
	DataResult<List<GetAllModelResponse>> getAll();
	DataResult<CreateModelResponse> add(CreatModelRequest creatModelRequest);
	DataResult<UpdateModelResponse> update(UpdateModelRequest updateModelRequest);
	Result delete(String id);
	void getModelName(String modelId);


	

}
