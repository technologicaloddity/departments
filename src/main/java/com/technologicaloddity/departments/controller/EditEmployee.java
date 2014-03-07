package com.technologicaloddity.departments.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import com.technologicaloddity.departments.dao.*;
import com.technologicaloddity.departments.model.*;

@Controller
@RequestMapping("/editEmployee.html")
public class EditEmployee extends MappedModelFormController<Employee> {

	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DepartmentPropertyEditor deparmentPropertyEditor;
	
	@Override
	public Class<Employee> getActualClass() {
		return Employee.class;
	}

	@ModelAttribute("allDepartments")
	public List<Department> getAllDepartments() {
		return departmentDao.findAll();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Department.class, "departments", deparmentPropertyEditor);
	}
	
	@Override
	@Autowired
	@Qualifier("employeeValidator")
	public void setMappedModelValidator(Validator validator) {
		this.validator = validator;		
	}

	@Override
	@Autowired
	@Qualifier("employeeDao")
	public void setModelDao(MappedModelDao<Employee> modelDao) {
		this.modelDao = modelDao;		
	}
	
}
