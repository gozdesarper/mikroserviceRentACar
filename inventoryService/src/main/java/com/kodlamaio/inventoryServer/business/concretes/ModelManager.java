package com.kodlamaio.inventoryServer.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filter.ModelUpdatedEvent;
import com.kodlamaio.common.result.DataResult;
import com.kodlamaio.common.result.Result;
import com.kodlamaio.common.result.SuccessDataResult;
import com.kodlamaio.common.result.SuccessResult;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryServer.business.abstracts.ModelService;
import com.kodlamaio.inventoryServer.business.request.create.CreatModelRequest;
import com.kodlamaio.inventoryServer.business.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryServer.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryServer.business.responses.get.GetAllModelResponse;
import com.kodlamaio.inventoryServer.business.responses.update.UpdateModelResponse;
import com.kodlamaio.inventoryServer.dataAccess.ModelRepository;
import com.kodlamaio.inventoryServer.entities.Model;
import com.kodlamaio.inventoryServer.kafka.InventoryProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	private InventoryProducer inventoryProducer;

	@Override
	public DataResult<List<GetAllModelResponse>> getAll() {
		List<Model> models = this.modelRepository.findAll();
		List<GetAllModelResponse> responses = models.stream()
				.map(model -> this.modelMapperService.forResponse().map(model, GetAllModelResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllModelResponse>>(responses,"Model Listed");
	}

	@Override
	public DataResult<CreateModelResponse> add(CreatModelRequest creatModelRequest) {
		checkIfExistByName(creatModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(creatModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		Model result = this.modelRepository.save(model);
		CreateModelResponse createModelResponse = this.modelMapperService.forResponse().map(result,
				CreateModelResponse.class);
		return new SuccessDataResult<CreateModelResponse>(createModelResponse,"model added");
		
	}
	
	@Override
	public DataResult<UpdateModelResponse> update(UpdateModelRequest updateModelRequest) {
		checkIfExistByName(updateModelRequest.getName());
		checkIfExistCarId(updateModelRequest.getId());
		Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
		Model result = this.modelRepository.save(model);
		
		ModelUpdatedEvent modelUpdatedEvent = this.modelMapperService.forRequest().map(model, ModelUpdatedEvent.class);
		modelUpdatedEvent.setModelId(result.getId());
		modelUpdatedEvent.setModelName(result.getName());
		modelUpdatedEvent.setMessage("Model Updated");
		this.inventoryProducer.sendMessage(modelUpdatedEvent);
		
		UpdateModelResponse createModelResponse = this.modelMapperService.forResponse().map(result,
				UpdateModelResponse.class);
		return new SuccessDataResult<UpdateModelResponse>(createModelResponse,"Model Updated");
	}

	@Override
	public Result delete(String id) {
		checkIfExistCarId(id);
		this.modelRepository.deleteById(id);
		return new SuccessResult("Car Deleted");
	}
	
	private void checkIfExistCarId (String id) {
		if(!modelRepository.existsById(id)) {
			throw new BusinessException("User not found");
		}
	}
	private void checkIfExistByName(String name) {
	Model model = modelRepository.findByNameIgnoreCase(name);
	if(model!=null) {
		throw new BusinessException("model name exist");
	}
}

	@Override
	public void getModelName(String modelId) {
	Model model = this.modelRepository.findById(modelId).get();
	model.getName();
		
	}

}
