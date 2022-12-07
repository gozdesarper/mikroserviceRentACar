package com.kodlamaio.inventoryServer.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filter.ModelUpdatedEvent;
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
	public List<GetAllModelResponse> getAll() {
		List<Model> models = this.modelRepository.findAll();
		List<GetAllModelResponse> responses = models.stream()
				.map(model -> this.modelMapperService.forResponse().map(model, GetAllModelResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public CreateModelResponse add(CreatModelRequest creatModelRequest) {
		checkIfExistByName(creatModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(creatModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		Model result = this.modelRepository.save(model);
		CreateModelResponse createModelResponse = this.modelMapperService.forResponse().map(result,
				CreateModelResponse.class);
		return createModelResponse;
		
	}
	
	@Override
	public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
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
		return createModelResponse;
	}

	@Override
	public void delete(String id) {
		checkIfExistCarId(id);
		this.modelRepository.deleteById(id);
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
}
