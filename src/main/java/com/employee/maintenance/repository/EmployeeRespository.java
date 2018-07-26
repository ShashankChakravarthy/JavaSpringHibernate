package com.employee.maintenance.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.employee.maintenance.entity.Employee;

public interface EmployeeRespository extends CrudRepository<Employee, Long>{

	List<Employee> findByName(String name);

	 

}
