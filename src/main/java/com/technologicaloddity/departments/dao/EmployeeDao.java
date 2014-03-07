package com.technologicaloddity.departments.dao;

import org.springframework.stereotype.*;

import com.technologicaloddity.departments.model.*;

@Component
public class EmployeeDao extends MappedModelDao<Employee> {

	@Override
	public Class<Employee> getActualClass() {
		return Employee.class;
	}
}
