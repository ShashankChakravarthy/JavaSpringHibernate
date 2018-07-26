package com.employee.maintenance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.maintenance.domain.SearchCriteria;
import com.employee.maintenance.entity.Employee;
import com.employee.maintenance.exception.DataFormatException;
import com.employee.maintenance.service.EmployeeService;
 

 

@RestController
@RequestMapping(value = "/example/v1/employee")
public class EmployeeController extends AbstractRestHandler {
	@Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)     
    public void createEmployee(@RequestBody Employee employee,
                                 HttpServletRequest request, HttpServletResponse response) {
        Employee createEmployee= this.employeeService.createEmployee(employee);
        response.setHeader("Employee", request.getRequestURL().append("/").append(createEmployee.getId()).toString());
    }
    
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEmployee( 
                                 @PathVariable("id") Long id, @RequestBody Employee Employee,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.employeeService.getEmployee(id));
        if (id != Employee.getId()) throw new DataFormatException("ID doesn't match!");
        this.employeeService.updateEmployee(Employee);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee getEmployee( 
                                 @PathVariable("id") Long id,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.employeeService.getEmployee(id));
         
        return this.employeeService.getEmployee(id);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee( 
                                 @PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.employeeService.getEmployee(id));
        this.employeeService.deleteEmployee(id);
    }
    
    @RequestMapping(value = "/search/criteria",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<Employee> getAllEmployees(@RequestBody SearchCriteria searchCriteria,HttpServletRequest request, HttpServletResponse response) {
        return this.employeeService.getAllEmployees(searchCriteria);
    }
}
