package com.example.department;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

	List <Department> getDepartmentData();

	Department updateDepartmentData(Department dept);

	Department saveDepartmentData(Department dept);

	Department getDeptById(long id);

	void deleteDepartmentData(long id);


}
