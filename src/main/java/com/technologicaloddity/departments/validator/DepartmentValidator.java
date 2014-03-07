package com.technologicaloddity.departments.validator;

import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.validation.*;

import com.technologicaloddity.departments.model.*;

@Component
public class DepartmentValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return (clazz.isAssignableFrom(Department.class));
	}

	public void validate(Object command, Errors errors) {
		Department department = (Department)command;
		if(!StringUtils.hasText(department.getName())) {
			errors.rejectValue("name", "error.required.name", "Name is required");
		}
		
	}
}
