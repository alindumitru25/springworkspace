package com.luv2code.spring.demo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	// controller method to show the form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	// controller method to process the form
	@RequestMapping("/processForm")
	public String showProcessForm() {
		return "helloworld";
	}
	
	// new controller method to read form data
	// add data to model
	@RequestMapping("/processFormTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		// read the request param from HTML form
		String theName = request.getParameter("studentName");
		
		// convert to uppercase
		theName = theName.toUpperCase();

		// create message
		String result = "yo! " + theName;

		// add message to the model
		model.addAttribute("message", result);

		return "helloworld";
	}

	@RequestMapping("/processFormThree")
	public String processFormThree(
			@RequestParam("studentName") String theName,
			Model model) {
		
		// convert to uppercase
		theName = theName.toUpperCase();

		// create message
		String result = "Hey my friend " + theName;

		// add message to the model
		model.addAttribute("message", result);

		return "helloworld";
	}
}
