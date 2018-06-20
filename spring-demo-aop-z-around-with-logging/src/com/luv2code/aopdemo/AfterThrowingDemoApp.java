package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			List<Account> accounts = accountDAO.getAccounts(tripWire);

			System.out.println("\nReceived the following accounts: " + accounts + "\n");
		} catch (Exception exc) {
			System.out.println("Something went wrong: " + exc);
		}
		context.close();
	}

}
