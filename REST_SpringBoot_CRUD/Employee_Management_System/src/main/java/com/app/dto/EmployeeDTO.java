package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.app.entities.EmploymentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * dept id can not be null : already added this on path var.
emp email must be valid min len=5 max len=20 (@Size)
password : alphanumeric n strong(special characters)
salary : min salary : 20000 max sal : 200000
join date : must be a future date
 */
@Getter
@Setter
@ToString
public class EmployeeDTO {
	@JsonProperty(access = Access.READ_ONLY) // used during serialization(skip it duirng de-ser)
	private Long id;
	private String firstName;
	private String lastName;
	@Email(message = "Invalid email !")
	@Size(min = 5, max = 20, message = "Invalid email length!")
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY) // skip this prop during ser. n in use it only during de-ser
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or invalid password!")
	private String password;
	@Future(message="Join date must be in future")
	private LocalDate joinDate;
	private EmploymentType empType;
	@Min(value = 20000,message = "sal must be > 20000")
	@Max(value = 200000,message = "sal must be < 200000")
	private double salary;
//	@JsonProperty(access = Access.WRITE_ONLY) // used during de-ser
//	private Long deptId;
}
