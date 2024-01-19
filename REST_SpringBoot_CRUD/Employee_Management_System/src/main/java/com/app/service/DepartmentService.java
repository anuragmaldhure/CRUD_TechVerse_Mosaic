package com.app.service;

import java.util.List;

import com.app.dto.DepartmentDTO;
import com.app.dto.DepartmentEmpsDTO;

public interface DepartmentService {

	DepartmentDTO addNewDepartment(DepartmentDTO dept);

	List<DepartmentDTO> getAllDepartments();

	DepartmentEmpsDTO getDeptAndEmpDetails(Long deptId);

}
