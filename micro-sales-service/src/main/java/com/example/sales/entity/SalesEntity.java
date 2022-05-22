package com.example.sales.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Table("Sales")
@Data
@NoArgsConstructor
public class SalesEntity {

	@Id
	private Long id;

	private String price;

	private String productName;

}
