package com.kodlamaio.invoiceService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.invoice.PaymentReceivedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.entity.Invoice;

@Service
public class RentalConsumer {
	
	private InvoiceService invoiceService;
	private ModelMapperService modelMapperService;
	
	private static final Logger LOGGER= LoggerFactory.getLogger(RentalConsumer.class);
	
	public RentalConsumer(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@KafkaListener(
			topics = "payment-received", 
			groupId = "invoice2"
			)
	public void consume(PaymentReceivedEvent event) {
		invoiceService.add(event);
	}
}