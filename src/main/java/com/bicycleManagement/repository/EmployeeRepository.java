package com.bicycleManagement.repository;

import com.bicycleManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,String> {

}
