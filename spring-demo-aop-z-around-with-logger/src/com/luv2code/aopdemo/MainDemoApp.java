package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		Account acc = new Account();
		acc.setName("Madhu");
		acc.setLevel("Platinum");
		
		accountDAO.addAccount(acc, true);
		
		// call getter/setter
		accountDAO.setName("name1");
		accountDAO.setServiceCode("silver");
		String name = accountDAO.getName();
		String code = accountDAO.getServiceCode();
		
		membershipDAO.addAccount();

		
		context.close();
	}

}
