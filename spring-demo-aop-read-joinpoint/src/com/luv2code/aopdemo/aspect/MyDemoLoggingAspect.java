package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	// @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDAOPackageWithoutGetterAndSetter()")
	public void beforeAddAccount(JoinPoint joinPoint) {
		System.out.println("\n======>> Executing @Before advice on addAccount() & returns\n");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		
		// display method arguments
		Object[] args = joinPoint.getArgs();
		
		// loop and print arguments
		for (Object tempArg: args) {
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				// downcast to Account and print specific stuff
				Account acc = (Account) tempArg;
				
				System.out.println("Account name: " + acc.getName());
				System.out.println("Account level: " + acc.getLevel());
			}
		}
	}
}
