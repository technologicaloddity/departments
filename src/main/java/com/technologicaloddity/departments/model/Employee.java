package com.technologicaloddity.departments.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee extends MappedModel {
	
	@Column(name="first_name", length=150, nullable=false, unique=false)
	private String firstName;
	
	@Column(name="last_name", length=150, nullable=false, unique=false)
	private String lastName;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="employee_department",
		joinColumns={@JoinColumn(name="employee_id",referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="deparment_id", referencedColumnName="id")}		
	)		
	private Set<Department> departments;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	
	
}
