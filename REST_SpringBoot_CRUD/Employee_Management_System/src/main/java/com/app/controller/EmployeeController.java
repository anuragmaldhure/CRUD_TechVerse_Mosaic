package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Employee;
import com.app.service.EmployeeService;

@RestController // =@Controller + @ResponseBody : added implicitly
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	// depcy
	@Autowired
	private EmployeeService empService;

	public EmployeeController() {
		System.out.println("in def ctor of " + getClass());
	}

	// URL : http://host:port/employees, method : GET
	// get all emps
	@GetMapping
	public List<Employee> getEmps() {
		System.out.println("in get emps");
		return empService.getAllEmps();
	}

	// URL : http://host:port/employees, method : POST
	// add new emp details
	@PostMapping
	public Employee addNewEmployee(@RequestBody Employee transientEmp) {
		System.out.println("in add new emp " + transientEmp);
		return empService.addNewEmp(transientEmp);
	}

	// URL : http://host:port/employees/{empId}, method : GET
	// get emp details by it's id
	@GetMapping("/{empId}") //template pattern
	public Employee getEmpDetails(@PathVariable Long empId) {
		System.out.println("in get emp " + empId);
		return empService.getEmpDetails(empId);

	}

	// URL : http://host:port/employees, method : PUT
	// COMPLETE updation of emp details
	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee detachedEmp) {
		System.out.println("in update emp " + detachedEmp);
		return empService.updateEmp(detachedEmp);
	}
	// URL : http://host:port/employees/{empId}, method : DELETE
	// delete emp details by id
	@DeleteMapping("/{empId}")
	public String deleteEmpDetails(@PathVariable Long empId)
	{
		System.out.println("in del emp "+empId);
		return empService.deleteEmp(empId);
	}

}
