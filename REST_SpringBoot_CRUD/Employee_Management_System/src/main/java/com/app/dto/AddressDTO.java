package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AddressDTO {	
	private String street;
	private String city;
	
	private String state;
	
	private String country;
	
	private String zipCode;
	
		
}
