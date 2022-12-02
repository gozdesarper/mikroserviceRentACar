package com.kodlamaio.inventoryServer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedEvent;
import com.kodlamaio.inventoryServer.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalConsumer {
	private CarService carService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);

    @KafkaListener(  // gelen veri varmı diye dinliyor.
            topics = "${spring.kafka.topic.name}" // topic name alıyor
            ,groupId = "${spring.kafka.consumer.group-id}" // inventory grup ismi gelen mesajı karşılıyor.
    )
 
    public void consume(RentalUpdatedEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        this.carService.updateCarState(event.getNewCarId(),3);
        this.carService.updateCarState(event.getOldCarId(),1);
    }
  public void consume(RentalCreatedEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        this.carService.updateCarState(event.getCarId(),3);
 }    
  }