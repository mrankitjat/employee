package com.nt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.model.EmployeeRequest;
import com.nt.model.EmployeeResponse;
import com.nt.repository.EmployeeRepository;
import com.nt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(EmployeeRequest employeeRequest) {
		validate(employeeRequest);
		String cleanMobileNo = employeeRequest.getMobileNo().replaceAll(" ", "");
		int otp = generateOTP(4);
		Employee employee = new Employee();
		employee.setMobileNo(cleanMobileNo);
		employee.setOtp(otp);
		Employee savedEmp = employeeRepository.save(employee);
		return "Employee saved successfully with id : "+savedEmp.getEmpId();
	}

	public static int generateOTP(int length) {
        Random random = new Random();

        int min = (int) Math.pow(10, length - 1); 
        int max = (int) Math.pow(10, length) - 1; 
        return random.nextInt((max - min) + 1) + min;
    }

	private void validate(EmployeeRequest employeeRequest) {
		if (employeeRequest.getMobileNo() == null || employeeRequest.getMobileNo().isEmpty()) {
			throw new RuntimeException("Mobile number is required");
		}
		
		if (!employeeRequest.getMobileNo().matches("\\d+")) {
			throw new RuntimeException("Mobile number should contain numbers only.");
		}
		
		if (employeeRequest.getMobileNo().length()!=10) {
			throw new RuntimeException("Mobile number should contain 10 digit numbers only.");
		}

	}
	
	@Override
	public List<EmployeeResponse> getAllEmployee() {
		List<EmployeeResponse> responseList = new ArrayList<>();
		employeeRepository.findAll().stream().forEach(emp->{
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setMobileNo(emp.getMobileNo());
			employeeResponse.setOtp(String.valueOf(emp.getOtp()));
			employeeResponse.setEmpId(String.valueOf(emp.getEmpId()));
			responseList.add(employeeResponse);
		});
		return responseList;
	}
	
	@Override
	public EmployeeResponse getEmployee(Long id) {
		Employee emp = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found by id : "+id));
		EmployeeResponse employeeResponse = new EmployeeResponse();
		employeeResponse.setMobileNo(emp.getMobileNo());
		employeeResponse.setOtp(String.valueOf(emp.getOtp()));
		employeeResponse.setEmpId(String.valueOf(emp.getEmpId()));
		return employeeResponse;
	}
}
