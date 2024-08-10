package com.raghu.spring.boot.rest.api.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghu.spring.boot.rest.api.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}