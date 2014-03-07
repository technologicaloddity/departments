package com.technologicaloddity.departments.model;

import java.beans.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.technologicaloddity.departments.dao.*;

@Component
public class DepartmentPropertyEditor extends PropertyEditorSupport {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public String getAsText() {
		String result = null;
		Department department = (Department)getValue();
		if(department != null) {
			result =  department.getId();
		}
		return result;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Department result = null;
		if(text != null && text.length() > 0) {
			result = departmentDao.findById(text);
		}
		setValue(result);
	}

}
