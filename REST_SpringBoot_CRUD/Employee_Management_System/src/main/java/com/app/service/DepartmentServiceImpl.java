package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.DepartmentDao;
import com.app.dto.DepartmentDTO;
import com.app.dto.DepartmentEmpsDTO;
import com.app.entities.Department;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	// dep
	@Autowired
	private DepartmentDao deptDao;
	//depcy : model mapper
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public DepartmentDTO addNewDepartment(DepartmentDTO dept) {
		Department department = deptDao.save(mapper.map(dept, Department.class));
		return mapper.map(department, DepartmentDTO.class);
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() {		
		return deptDao.findAll()
				.stream() //Stream<Dept>
				.map(dept -> mapper.map(dept,DepartmentDTO.class)) //Stream <DTO>
				.collect(Collectors.toList());//List<DTO>	
				
	}

	@Override
	public DepartmentEmpsDTO getDeptAndEmpDetails(Long deptId) {
		// TODO Auto-generated method stub
		Department departmentEntity = deptDao.getDepartmentAndEmpDetails(deptId);
		//map entity --> dto
		return mapper.map(departmentEntity, DepartmentEmpsDTO.class);
	}
	
	

}
