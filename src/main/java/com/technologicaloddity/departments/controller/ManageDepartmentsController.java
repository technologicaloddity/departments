package com.technologicaloddity.departments.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.technologicaloddity.departments.dao.*;
import com.technologicaloddity.departments.model.*;

@Controller
public class ManageDepartmentsController {

	@Autowired
	private DepartmentDao departmentDao;
	
	@ModelAttribute("departments")
	public List<Department> getDepartments() {
		return departmentDao.findAll();
	}
	
	@RequestMapping("/manageDepartments.html")
	public String handleRequest() {
		return "manageDepartments";
	}
	
}
