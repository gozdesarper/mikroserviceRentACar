package com.kodlamaio.common.events.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelCreatedEvent {
	private String message;
	private String brandId;
	private String brandName;
	private String modelId;
	private String modelName;

}
