package com.app.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentEmpsDTO {
	@JsonProperty(access = Access.READ_ONLY) //used during serialization
	private Long id;
	
	//assume : json prop name : deptLocation
	@JsonProperty(value = "deptLocation") //only for eg.
	private String location;

	private String name;
	//additional prop.
	private List<EmployeeDTO> employees;
}
