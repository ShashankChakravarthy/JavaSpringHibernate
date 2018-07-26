package com.employee.maintenance.service;

 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.maintenance.domain.SearchCriteria;
import com.employee.maintenance.entity.Employee;
import com.employee.maintenance.repository.EmployeeRespository;

@Component
public class EmployeeService {
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	private EmployeeRespository employeeRepository;
	public Employee createEmployee(Employee station) {
		 
		return employeeRepository.save(station);
	}

	public Employee getEmployee(Long id) {
		 
		return employeeRepository.findById(id).get();
	}

	public void updateEmployee(Employee station) {
		employeeRepository.save(station);
		
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		
	}

	public List<Employee> getAllEmployees(SearchCriteria searchCriteria) {
		if(searchCriteria.getName() != null) {
			return employeeRepository.findByName(searchCriteria.getName());
		}  
		return (List<Employee>) employeeRepository.findAll();
	}

}
