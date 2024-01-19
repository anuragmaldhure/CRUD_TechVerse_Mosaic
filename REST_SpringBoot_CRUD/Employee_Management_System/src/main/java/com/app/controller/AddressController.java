package com.app.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;
import com.app.service.AddressService;

@RestController
@RequestMapping("/address")
@Validated
public class AddressController {
	// dep
	@Autowired
	private AddressService adrService;

	// add a REST API end point to create new adr , assigned to the emp
	// http://host:port/address/{empId} , method=POST
	// payload (req body) : transient adr
	@PostMapping("/{empId}")
	public ApiResponse assignAddressToEmp(@PathVariable 
			@NotNull(message = "Emp id is required !!!")  Long empId, @RequestBody AddressDTO adr) {
		System.out.println("in assign adr " + adr);
		try {
			return adrService.assignEmpAddress(empId, adr);
		} catch (Exception e) {
			System.out.println(e);
			return new ApiResponse(e.getMessage());
		}
	}

}
