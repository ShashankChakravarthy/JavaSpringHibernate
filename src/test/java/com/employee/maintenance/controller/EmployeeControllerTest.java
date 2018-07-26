package com.employee.maintenance.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.employee.maintenance.controller.EmployeeController;
import com.employee.maintenance.domain.SearchCriteria;
import com.employee.maintenance.entity.Employee;
import com.employee.maintenance.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeControllerTest {
	private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                 
                .build();
    }
    
	@Test
	public void testCreateemployee() throws Exception {
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	     
	    Mockito.when(employeeService.createEmployee(employee)).thenReturn(employee);
	    mockMvc.perform(
	            post("/example/v1/employees")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(employee)))
	            .andExpect(status().isCreated())
	            ;
	     
	    verify(employeeService, times(1)).createEmployee(employee);
	    verifyNoMoreInteractions(employeeService);
	}

	@Test
	public void testUpdateemployee() throws Exception {
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	    employee.setId(1l); 
	    Mockito.doNothing().when(employeeService).updateEmployee(employee);
	    Mockito.when(employeeService.getEmployee(Mockito.anyLong())).thenReturn(employee);
	    mockMvc.perform(
	            put("/example/v1/employees/1")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(employee)))
	            .andExpect(status().isAccepted())
	            ;
	     
	    verify(employeeService, times(1)).updateEmployee(employee);
	     
	}

	@Test
	public void testDeleteemployee() throws Exception{
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	    employee.setId(1l); 
	    Mockito.doNothing().when(employeeService).deleteEmployee(Mockito.anyLong());
	    Mockito.when(employeeService.getEmployee(Mockito.anyLong())).thenReturn(employee);
		mockMvc.perform(
	            delete("/example/v1/employees/1")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    )
	            .andExpect(status().isOk())
	            ;
	     
	    verify(employeeService, times(1)).deleteEmployee(employee.getId());
	     
	}

	@Test
	public void testGetAllemployees() throws Exception{
		Employee employee = new Employee();
		 
		employee.setName("name");
		 
	    employee.setId(1l); 
	     
	    List<Employee> list = new ArrayList<Employee>();
	    list.add(employee);
	    SearchCriteria searchCriteria = new SearchCriteria();
		Mockito.when(employeeService.getAllEmployees(Mockito.any(SearchCriteria.class) )).thenReturn(list);
		mockMvc.perform(
	            get("/example/v1/employees")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(searchCriteria)))
	            .andExpect(status().isOk())
	            ;
	     
	    verify(employeeService, times(1)).getAllEmployees(searchCriteria);
	     
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
