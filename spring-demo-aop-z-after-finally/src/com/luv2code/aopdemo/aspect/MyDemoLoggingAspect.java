package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.getAccounts(..))")
	public void afterFinallyGetAccountsAdvice(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();

		System.out.println("Executing @After on method: " + method);
	}
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.getAccounts(..))",
			throwing="exc")
		public void afterThrowingGetAccountsAdvice(JoinPoint joinPoint, Throwable exc) {
			String method = joinPoint.getSignature().toShortString();

			System.out.println("Executing @AfterThrowing on method: " + method);
			System.out.println("Result @AfterThrowing is: " + exc);
		}
	
	@AfterReturning(
		pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.getAccounts(..))",
		returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		String method = joinPoint.getSignature().toShortString();

		System.out.println("Executing @AfterReturning on method: " + method);
		System.out.println("Result @AfterReturning is: " + result);
		
		// modify data before returning to the caller
		convertAccountNamesToUpperCase(result);
		
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		for (Account tempAccount: result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
	}

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
