package com.technologicaloddity.departments.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.technologicaloddity.departments.dao.*;
import com.technologicaloddity.departments.model.*;

@Controller
@RequestMapping("/editDepartment.html")
public class EditDepartment extends MappedModelFormController<Department> {

	@Override
	public Class<Department> getActualClass() {
		return Department.class;
	}

	@Override
	@Autowired
	@Qualifier("departmentValidator")
	public void setMappedModelValidator(Validator validator) {
		this.validator = validator;		
	}

	@Override
	@Autowired
	@Qualifier("departmentDao")
	public void setModelDao(MappedModelDao<Department> modelDao) {
		this.modelDao = modelDao;		
	}
}
