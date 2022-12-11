package com.kodlamaio.invoiceService.business.concrete;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.invoice.PaymentReceivedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.request.UpdateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.UpdateInvoiceResponse;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entity.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	public CreateInvoiceResponse add(PaymentReceivedEvent paymentReceivedEvent) {
		Invoice invoice = this.modelMapperService.forRequest().map(paymentReceivedEvent, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());
		invoiceRepository.save(invoice);
		CreateInvoiceResponse createInvoiceResponse = this.modelMapperService.forResponse().map(invoice,CreateInvoiceResponse.class);
		return createInvoiceResponse;
	}
	@Override
	public UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());
		invoiceRepository.save(invoice);
		UpdateInvoiceResponse updateInvoiceResponse  = this.modelMapperService.forResponse().map(invoice,UpdateInvoiceResponse.class);
		return updateInvoiceResponse;
	}
	@Override
	public GetAllInvoiceResponse getall() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoiceResponse> getAllInvoiceResponses = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoice, GetAllInvoiceResponse.class))
				.collect(Collectors.toList());
		return (GetAllInvoiceResponse) getAllInvoiceResponses;
	}

	@Override
	public void delete(String id) {
		this.invoiceRepository.deleteAll();
		
	}
	

}
