package com.technologicaloddity.departments.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

	@RequestMapping("/index.html")
	public String showIndex() {
		return "index";
	}	
}
