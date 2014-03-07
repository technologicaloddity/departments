package com.technologicaloddity.departments.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="department")
public class Department extends MappedModel {	
	
	@Column(name="name", length=150, nullable=false, unique=true)
	private String name;

	@ManyToMany(mappedBy="departments",fetch=FetchType.LAZY)	
	private Set<Employee> employees;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
}
