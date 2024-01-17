package com.app.service;

import java.util.List;

import com.app.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEmps();

	Employee addNewEmp(Employee transientEmp);

	Employee getEmpDetails(Long empId);

	Employee updateEmp(Employee detachedEmp);

	String deleteEmp(Long empId);
}
