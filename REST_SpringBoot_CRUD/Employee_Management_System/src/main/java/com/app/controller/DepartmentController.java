package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DepartmentDTO;
import com.app.dto.DepartmentEmpsDTO;
import com.app.entities.Department;
import com.app.service.DepartmentService;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {
	// dep :
	@Autowired // (required = true)
	private DepartmentService deptService;

	public DepartmentController() {
		System.out.println("in ctor of " + getClass());
	}

	// REST API end point
	// URL : http://localhost:8080/departments
	// Method : POST
	// payload : JSON representation of dept req dto
	// resp : Department resp dto
	@PostMapping
	public DepartmentDTO addNewDepartment(@RequestBody DepartmentDTO dept) {
		System.out.println("in add new dept " + dept);
		return deptService.addNewDepartment(dept);
	}

	// REST API end point
	// URL : http://localhost:8080/departments
	// Method : GET
	// resp : List<DTO>
	@GetMapping
	public List<DepartmentDTO> listAllDepartments() {
		System.out.println("in list all  depts");
		return deptService.getAllDepartments();
	}
	// REST API end point
		// URL : http://localhost:8080/departments/{deptId}
		// Method : GET
		// resp :
	@GetMapping("/{deptId}")
	public DepartmentEmpsDTO getDeptAndEmpDetails(@PathVariable Long deptId)
	{
		System.out.println("in get dept n emps "+deptId);
		return deptService.getDeptAndEmpDetails(deptId);
	}

}
