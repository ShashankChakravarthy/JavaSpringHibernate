package com.employee.maintenance.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.employee.maintenance.domain.SearchCriteria;
import com.employee.maintenance.entity.Employee;
import com.employee.maintenance.repository.EmployeeRespository;
import com.employee.maintenance.service.EmployeeService;

public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRespository employeeRepository;
	
	@InjectMocks
	EmployeeService employeeService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateemployee() {
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	    Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		employee = employeeService.createEmployee(employee);
		Assert.assertNotNull(employee);
	}

	@Test
	public void testGetemployee() {
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
		Optional<Employee> optionemployee = Optional.of(employee);
	    Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(optionemployee);
	    employeeService.getEmployee(1l) ;
	}

	@Test
	public void testUpdateemployee() {
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	    Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		employeeService.updateEmployee(employee);
	}

	@Test
	public void testDeleteemployee() {
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	    Mockito.doNothing().when(employeeRepository).deleteById(Mockito.anyLong());
		employeeService.deleteEmployee(1l);
	}

	@Test
	public void testGetAllemployees() {
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	    employee.setId(1l); 
	     
	    List<Employee> list = new ArrayList<Employee>();
	    list.add(employee);
	    SearchCriteria searchCriteria = new SearchCriteria();
	    Mockito.when(employeeRepository.findAll()).thenReturn(list);
		employeeService.getAllEmployees(searchCriteria);
	}

}
