package com.technologicaloddity.departments.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.technologicaloddity.departments.dao.*;
import com.technologicaloddity.departments.model.*;

@Controller
public class DeletionController {

	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping("/deleteEmployee.html")
	public String deleteEmployee(@RequestParam("id") String id) {
		Employee employee = employeeDao.findById(id);
		if(employee != null) {
			employeeDao.delete(employee);
		}
		return "redirect:/manageEmployees.html";
	}
	
	@RequestMapping("/deleteDepartment.html")
	public String deleteDepartment(@RequestParam("id") String id) {
		Department department = departmentDao.findById(id);
		if(department != null) {
			departmentDao.delete(department);
		}
		return "redirect:/manageDepartments.html";
	}
	
}
