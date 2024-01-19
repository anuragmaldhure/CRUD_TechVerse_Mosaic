package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentDTO {
	@JsonProperty(value="dept_id",access = Access.READ_ONLY)
	private Long id;	
	private String name;	
	private String location;
}
