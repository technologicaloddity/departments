package com.technologicaloddity.departments.dao;

import java.util.*;

import org.hibernate.*;
import org.springframework.stereotype.*;

import com.technologicaloddity.departments.model.*;

@Component
public class DepartmentDao extends MappedModelDao<Department> {

	@Override
	public Class<Department> getActualClass() {
		return Department.class;
	}
	
	@Override
	public void delete(Department entity) {
		removeEmployeesFromDepartment(entity);
		super.delete(entity);
	}
	
	private void removeEmployeesFromDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		
		Set<Employee> employees = department.getEmployees();
		if(!Hibernate.isInitialized(employees)) {
			session.lock(department, LockMode.NONE);
			Hibernate.initialize(employees);
		}
						
		if(employees != null) {			
			for(Employee employee : employees) {
				Set<Department> departments = employee.getDepartments();
				departments.remove(department);
				employee.setDepartments(departments);
				session.saveOrUpdate(employee);
			}
		}
		
	}

}
