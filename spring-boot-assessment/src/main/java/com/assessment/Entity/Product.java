package com.assessment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Product {

	@Id
	@NotBlank
	@Size(min = 1, max = 9)
	private String productId;

	@NotBlank
	private String productName;

	@NotNull
	@DecimalMin(value = 0.1 + "", message = "Product Price Cannot Be Negative Or 0")
	@DecimalMax(value = Double.MAX_VALUE + "")
	private double productPrice;

	@NotNull
	@Min(value = 1)
	@Max(value = Integer.MAX_VALUE, message = "Product Quantity Cannot Be Negative Or 0")
	private int productQuantity;

}
