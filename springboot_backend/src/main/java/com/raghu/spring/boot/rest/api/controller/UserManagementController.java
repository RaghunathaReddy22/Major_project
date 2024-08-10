package com.raghu.spring.boot.rest.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghu.spring.boot.rest.api.entity.Employee;
import com.raghu.spring.boot.rest.api.exception.ResourceNotFoundException;
import com.raghu.spring.boot.rest.api.repositary.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserManagementController {

	Logger logger = LoggerFactory.getLogger(UserManagementController.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	// getting the employees list
	@GetMapping(value = "/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// adding the employee into the database
	@PostMapping("/employees")
	public Employee addemployee(@RequestBody Employee Employee) {
		return employeeRepository.save(Employee);
	}

	// get employee data from the database
	@GetMapping("/employees/{userId}")
	public ResponseEntity<Employee> getbyID(@PathVariable long userId) {
		Employee employee = employeeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found with" + userId));
		return ResponseEntity.ok(employee);
	}

	// update Employee Details
	@PutMapping("/employees/{userId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long userId, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found with" + userId));
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		Employee updatedDetails = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedDetails);
	}
	//Delete Employee Details
	@DeleteMapping("/employees/{userId}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long userId){
		Employee employee = employeeRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("employee not found with" + userId));

		employeeRepository.delete(employee);
		Map<String,Boolean> response=new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

}
