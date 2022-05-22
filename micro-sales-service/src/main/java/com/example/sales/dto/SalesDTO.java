package com.example.sales.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalesDTO {

	private Long id;

	private String price;

	private String productName;

}