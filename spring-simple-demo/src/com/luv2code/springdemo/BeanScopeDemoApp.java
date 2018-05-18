package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		// load the spring config files
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		// check if they are the same bean
		boolean result = theCoach == alphaCoach;
		
		// print out result
		System.out.println("\nIdentity check: " + result);
		
		System.out.println("\nMemory loc theCoach: " + theCoach);
		System.out.println("\nMemory loc alphaCoach: " + alphaCoach);
	}

}
