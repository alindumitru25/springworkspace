package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we add advices / before / after / around
	@Before("execution(public void add*())")
	public void beforeAddAccountAdvice() {
		System.out.println("\n======>> Executing @Before advice on addAccount()\n");
	}
	
	@Before("execution(String add*())")
	public void beforeAddReturnString() {
		System.out.println("\n======>> Executing @Before advice on addAccount() & returns\n");
	}
}
