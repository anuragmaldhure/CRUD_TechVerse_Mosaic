package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AddressDao;
import com.app.dao.EmployeeDao;
import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;
import com.app.entities.Address;
import com.app.entities.Employee;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	//dep : 
	@Autowired
	private AddressDao adrDao;
	@Autowired
	private EmployeeDao empDao;	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse assignEmpAddress(Long empId, AddressDTO adr) {
		// get the proxy of emp 
		Employee emp=empDao.getReferenceById(empId);//no select
		//adr dto --> adr entity
		Address addressEntity = mapper.map(adr, Address.class);
		//set up uni dir asso adr --> emp
		addressEntity.setEmp(emp);//assigning a placeholder : proxy
		adrDao.save(addressEntity);
		return new  ApiResponse("address assigned to emp....");
	}

}
