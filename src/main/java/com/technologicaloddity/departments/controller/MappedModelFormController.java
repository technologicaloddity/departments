package com.technologicaloddity.departments.controller;

import org.springframework.dao.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.technologicaloddity.departments.dao.*;
import com.technologicaloddity.departments.model.*;

@Controller
public abstract class MappedModelFormController<T extends MappedModel> {	
	
	protected Validator validator;
	
	protected MappedModelDao<T> modelDao;
	
	public abstract Class<T> getActualClass();
	
	public abstract void setMappedModelValidator(Validator validator);
	
	public abstract void setModelDao(MappedModelDao<T> modelDao);
	
	private String getTypeName() {
		return getActualClass().getSimpleName();
	}
	
	@RequestMapping(method=RequestMethod.GET,params="action=edit")
	public String showForm(Model model,@RequestParam(value="id",required=false) String id) {
		T object = null;
		if(id == null) {
			// add new
			try {
				object = getActualClass().newInstance();				
			} catch(Exception ie) {
				ie.printStackTrace();
			}
		} else {
			// edit
			object = modelDao.findById(id);			
		}
		model.addAttribute("mappedModel", object);
		model.addAttribute("mappedModelName", getTypeName());
		return getEditView();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String handleSubmit(Model model, @ModelAttribute("mappedModel") T mappedModel, BindingResult bindingResult, @RequestParam(value="submit",required=true) String submitType) {
		String result = makeRedirect(getManageView());
		if(submitType.toLowerCase().equals("save")) {
			validate(mappedModel, bindingResult);
			if(bindingResult.hasErrors()) {
				model.addAllAttributes(bindingResult.getAllErrors());
				model.addAttribute(mappedModel);
				model.addAttribute("mappedModelName", getTypeName());
				result = getEditView();			
			} else {
				try {
					modelDao.saveOrUpdate(mappedModel);
				} catch(DataIntegrityViolationException dive) {
					String message = dive.getMostSpecificCause().getMessage();
					bindingResult.reject("error.constraint.violation", message);					 
					model.addAllAttributes(bindingResult.getAllErrors());
					model.addAttribute("mappedModel", mappedModel);
					model.addAttribute("mappedModelName", getTypeName());
					result = getEditView();				
				}
			}
		}
		return result;		
	}
	
	protected void validate(T mappedModel, BindingResult bindingResult) {
		if(this.validator != null) {
			this.validator.validate(mappedModel, bindingResult);
		}
	}
	
	public String makeRedirect(String viewName) {
		return "redirect:/" + viewName + ".html";
	}
	
	public String getEditView() {
		return "edit" + getTypeName();
	}
	
	public String getDeleteView() {
		return "delete" + getTypeName();
	}
	
	public String getManageView() {
		return "manage" + getTypeName() + "s";
	}
	
}
