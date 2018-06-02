package com.luv2code.spring.demo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// add init binder for trimming
	// intercepts all requests
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		// create a new Student object
		Customer theCustomer = new Customer();
		
		// add that student object as model attribute
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@RequestMapping("/processForm")
	public String showProcessForm(
			@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "customer-form";
		}
		
		return "customer-confirmation";
	}
}
