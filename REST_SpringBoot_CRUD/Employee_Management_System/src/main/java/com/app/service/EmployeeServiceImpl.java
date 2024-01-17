package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.EmployeeDao;
import com.app.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	// depcy
	@Autowired
	private EmployeeDao empDao;

	@Override
	public List<Employee> getAllEmps() {

		return empDao.findAll();
	}

	@Override
	public Employee addNewEmp(Employee transientEmp) {
		// CrudRepository method : T save (T entity)
		return empDao.save(transientEmp);
	}// rets DETACHED emp to the caller

	@Override
	public Employee getEmpDetails(Long empId) {
		// TODO Auto-generated method stub
		return empDao.findById(empId).orElseThrow(() 
				-> new ResourceNotFoundException("Invalid emp id !!!!"));
	}
	// in case of valid id : rets DETACHED emp
	// in case of invalid id : throws Custom exc

	@Override
	public Employee updateEmp(Employee detachedEmp) {
		// chk if emp exists
		if(empDao.existsById(detachedEmp.getId())) {//select
			//exists --update
			return empDao.save(detachedEmp);
		}
		throw new ResourceNotFoundException("Invalid emp id !!!!");
	}//tx.commit() --> update query

	@Override
	public String deleteEmp(Long empId) {
		if(empDao.existsById(empId))
		{
			empDao.deleteById(empId);
			return "deleted emp details...";
		}
		return "deletion of emp details failed !!!!!";
	}

	
	
	

}
