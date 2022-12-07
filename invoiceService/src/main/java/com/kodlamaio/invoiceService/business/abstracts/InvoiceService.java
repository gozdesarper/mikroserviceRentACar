package com.kodlamaio.invoiceService.business.abstracts;

import com.kodlamaio.invoiceService.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.request.UpdateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.business.response.UpdateInvoiceResponse;
import com.kodlamaio.invoiceService.entity.Invoice;

public interface InvoiceService {
	
	CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
	UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest);
	void createInvoice(Invoice invoice);
	GetAllInvoiceResponse getall();

}
