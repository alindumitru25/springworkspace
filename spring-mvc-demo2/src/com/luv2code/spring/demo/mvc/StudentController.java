package com.luv2code.spring.demo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		// create a new Student object
		Student theStudent = new Student();
		
		// add that student object as model attribute
		theModel.addAttribute("student", theStudent);
		
		return "student-form";
	}
	
	@RequestMapping("/processForm")
	public String showProcessForm(@ModelAttribute("student") Student theStudent) {
		return "student-confirmation";
	}
}
