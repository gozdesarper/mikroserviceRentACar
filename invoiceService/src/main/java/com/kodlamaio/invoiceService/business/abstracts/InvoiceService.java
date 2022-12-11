package com.kodlamaio.invoiceService.business.abstracts;

import com.kodlamaio.common.events.invoice.PaymentReceivedEvent;
import com.kodlamaio.invoiceService.business.request.UpdateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.UpdateInvoiceResponse;

public interface InvoiceService {
	
	CreateInvoiceResponse add(PaymentReceivedEvent paymentReceivedEvent);
	UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest);
	GetAllInvoiceResponse getall();
	void delete (String id);

}
