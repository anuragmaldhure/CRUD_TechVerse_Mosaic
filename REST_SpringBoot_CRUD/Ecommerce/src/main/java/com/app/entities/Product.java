package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products") // to specify table name
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product extends BaseEntity{
	
	@Column(length = 20, unique = true)
	private String name;
	
	@Column(length = 100)
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	private double price;
	
	private int availableStock;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;

}

