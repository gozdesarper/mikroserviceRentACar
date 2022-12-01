package com.kodlamaio.inventoryServer.business.request.create;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatCarRequest {
	@Min(0)
	private double dailyPrice;
	@Min(2015)
	private int modelYear;
	@NotNull
	@NotBlank
	private String plate;
	@Min(0)
	@Max(3)
	private int state;
	@NotNull
	@NotBlank
	private String modelId;
}
