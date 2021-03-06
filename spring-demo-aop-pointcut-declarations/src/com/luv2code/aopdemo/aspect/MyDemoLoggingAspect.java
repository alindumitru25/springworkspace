package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDAOPackage() {}

	// this is where we add advices / before / after / around
	@Before("forDAOPackage()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n======>> Executing @Before advice on addAccount()\n");
	}
	
	// @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
	@Before("forDAOPackage()")
	public void beforeAddReturnString() {
		System.out.println("\n======>> Executing @Before advice on addAccount() & returns\n");
	}
	
	@Before("forDAOPackage()")
	public void performApiAnalytics() {
		System.out.println("\n======>> Perform API analytics\n");
	}
}
