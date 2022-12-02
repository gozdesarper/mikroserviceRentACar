package com.kodlamaio.rentalService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RentalProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalProducer.class);  //consola mesaj yazmak için.BU CLASSDAKİ İNFO İÇİN.

	private NewTopic topic; 
	
	private KafkaTemplate<String, RentalCreatedEvent> kafkaCreatedTemplate;

	private KafkaTemplate<String, RentalUpdatedEvent> kafkaUpdatedTemplate;


	public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
		
		LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString())); //LOGGER.İNFO LOGGER DA GÖZÜKMESİ GEREKEN 
		
		Message<RentalCreatedEvent> message = MessageBuilder.withPayload(rentalCreatedEvent).setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaCreatedTemplate.send(message);  // MESAJI GÖNDERDİ.
	}
	
	
	public void sendMessage(RentalUpdatedEvent rentalUpdatedEvent) {
		
		LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString())); //LOGGER.İNFO LOGGER DA GÖZÜKMESİ GEREKEN 
		
		Message<RentalUpdatedEvent> message = MessageBuilder.withPayload(rentalUpdatedEvent).setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaUpdatedTemplate.send(message);  // MESAJI GÖNDERDİ.
	}
}
