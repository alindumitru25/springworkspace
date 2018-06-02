package com.luv2code.spring.demo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SIllyController {
	@RequestMapping("/showForm")
	public String displayForm() {
		return "silly";
	}
}
