package com.example.department;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DepartmentServiceImpl implements DepartmentService{

	 @Autowired
	    DepartmentRepository deptRepository;

	 
	@Override
	public List<Department> getDepartmentData() {
		return deptRepository.findAll();
		
	}

	@Override
	public Department updateDepartmentData(Department dept) {
		return deptRepository.save(dept);
	}

	@Override
	public Department saveDepartmentData(Department dept) {
		 return deptRepository.save(dept);
	}

	@Override
	public Department getDeptById(long id) {
		Optional<Department> e = deptRepository.findById(id);
		return e.get();
	}

	@Override
	public void deleteDepartmentData(long id) {
		deptRepository.deleteById(id);
		
	}

}
