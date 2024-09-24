package com.nt.service;

import java.util.List;

import com.nt.model.EmployeeRequest;
import com.nt.model.EmployeeResponse;

public interface EmployeeService {

	String saveEmployee(EmployeeRequest employeeRequest);

	List<EmployeeResponse> getAllEmployee();

	EmployeeResponse getEmployee(Long id);

}
