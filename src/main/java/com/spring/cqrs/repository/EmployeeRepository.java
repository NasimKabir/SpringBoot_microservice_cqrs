package com.spring.cqrs.repository;

import com.spring.cqrs.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeId(long empId);
}
