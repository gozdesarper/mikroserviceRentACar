package com.kodlamaio.inventoryServer.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse {

	
	
	private String id;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private String modelName;
	private int state;
}
