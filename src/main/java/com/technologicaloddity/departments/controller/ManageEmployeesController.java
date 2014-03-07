package com.technologicaloddity.departments.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.technologicaloddity.departments.dao.*;
import com.technologicaloddity.departments.model.*;

@Controller
public class ManageEmployeesController {

	@Autowired
	private EmployeeDao employeeDao;
	
	@ModelAttribute("employees")
	public List<Employee> getEmployees() {
		return employeeDao.findAll();
	}
	
	@RequestMapping("/manageEmployees.html")
	public String handleRequest() {
		return "manageEmployees";
	}	
}
