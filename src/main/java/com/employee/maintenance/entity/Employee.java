package com.employee.maintenance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
 
public class Employee {
	@Id
	@SequenceGenerator(name = "employee_generator", sequenceName = "employee_sequence", allocationSize = 1)
	@GeneratedValue(generator = "employee_generator")
	private Long id;

	private String address, name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
