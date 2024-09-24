package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.EmployeeRequest;
import com.nt.model.EmployeeResponse;
import com.nt.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/save")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest request){
		try {
			return new ResponseEntity<String>(employeeService.saveEmployee(request), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployee(){
		try {
			return new ResponseEntity<List<EmployeeResponse>>(employeeService.getAllEmployee(), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/by/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable Long id){
		try {
			return new ResponseEntity<EmployeeResponse>(employeeService.getEmployee(id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
