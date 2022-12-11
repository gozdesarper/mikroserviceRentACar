package com.kodlamaio.invoiceService.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.events.invoice.PaymentReceivedEvent;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.request.UpdateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.UpdateInvoiceResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
public class InvoiceController {
	private InvoiceService invoiceService;
	
	@PostMapping()
	CreateInvoiceResponse add(@RequestBody PaymentReceivedEvent paymentReceivedEvent) {
		return this.invoiceService.add(paymentReceivedEvent);
	}
	@PutMapping()
	UpdateInvoiceResponse update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.update(updateInvoiceRequest);
	}
	@GetMapping()
	GetAllInvoiceResponse getall() {
		return this.invoiceService.getall();
	}
	
	@DeleteMapping()
	void delete (String id) {
	this.invoiceService.delete(id);
	}
	
}
