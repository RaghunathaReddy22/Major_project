package com.raghu.spring.boot.rest.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghu.spring.boot.rest.api.repositary.EmployeeRepository;
@Service
public class UserManagementServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

}
