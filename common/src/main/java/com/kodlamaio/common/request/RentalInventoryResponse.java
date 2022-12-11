package com.kodlamaio.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalInventoryResponse {
	
	private String brandName;
	private String modelName;
	private int modelYear;
	

}
