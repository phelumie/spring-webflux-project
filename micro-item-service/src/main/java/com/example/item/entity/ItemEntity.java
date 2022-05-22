package com.example.item.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;

@Table("item")
@Data
@NoArgsConstructor
@Entity
public class ItemEntity {
	@Id
	private Long id;
	private String itemName;
	private String itemSize;

}
