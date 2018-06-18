package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	// @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDAOPackageWithoutGetterAndSetter()")
	public void beforeAddAccount() {
		System.out.println("\n======>> Executing @Before advice on addAccount() & returns\n");
	}
	
}
