package com.kodlamaio.filterService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filter.BrandUpdatedEvent;
import com.kodlamaio.common.events.filter.CarCreatedEvent;
import com.kodlamaio.common.events.filter.CarDeletedEvent;
import com.kodlamaio.common.events.filter.CarUpdatedEvent;
import com.kodlamaio.common.events.filter.ModelUpdatedEvent;
import com.kodlamaio.filterService.business.abstracs.FilterService;

@Service
public class InventroyConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(InventroyConsumer.class);
	
	private FilterService filterService; 
	
	public InventroyConsumer(FilterService filterService) {
		super();
		this.filterService = filterService;
	}
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "create")
	public void consume(CarCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		filterService.addCar(event);
	}
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "updatecar")
	public void consume(CarUpdatedEvent event) {
		LOGGER.info(String.format("Car event received in filter service => %s", event.toString()));
		filterService.updateCar(event);
		
	}
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "deletecar")
	public void consume(CarDeletedEvent event) {
		LOGGER.info(String.format("Car event received in filter service => %s", event.toString()));
		filterService.deleteCar(event);
		
	}
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "updatebrand")
	public void consume(BrandUpdatedEvent event) {
		LOGGER.info(String.format("Brand event received in filter service => %s", event.toString()));
		filterService.updateBrand(event);
		
	}
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "updatemodel")
	public void consume(ModelUpdatedEvent event) {
		LOGGER.info(String.format("Model event received in filter service => %s", event.toString()));
		filterService.updateModel(event);
		
	}
}