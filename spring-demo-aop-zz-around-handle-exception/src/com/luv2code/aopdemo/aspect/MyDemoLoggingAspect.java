package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String method = proceedingJoinPoint.getSignature().toShortString();
		myLogger.info("Executing @Around on method: " + method);
		
		long begin = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		long duration = end - begin;
		myLogger.info("Duration took: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.getAccounts(..))")
	public void afterFinallyGetAccountsAdvice(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();

		myLogger.info("Executing @After on method: " + method);
	}
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.getAccounts(..))",
			throwing="exc")
		public void afterThrowingGetAccountsAdvice(JoinPoint joinPoint, Throwable exc) {
			String method = joinPoint.getSignature().toShortString();

			myLogger.info("Executing @AfterThrowing on method: " + method);
			myLogger.info("Result @AfterThrowing is: " + exc);
		}
	
	@AfterReturning(
		pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.getAccounts(..))",
		returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		String method = joinPoint.getSignature().toShortString();

		myLogger.info("Executing @AfterReturning on method: " + method);
		myLogger.info("Result @AfterReturning is: " + result);
		
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
		myLogger.info("\n======>> Executing @Before advice on addAccount() & returns\n");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		myLogger.info("Method: " + methodSig);
		
		// display method arguments
		Object[] args = joinPoint.getArgs();
		
		// loop and print arguments
		for (Object tempArg: args) {
			myLogger.info(tempArg.toString());
			
			if (tempArg instanceof Account) {
				// downcast to Account and print specific stuff
				Account acc = (Account) tempArg;
				
				myLogger.info("Account name: " + acc.getName());
				myLogger.info("Account level: " + acc.getLevel());
			}
		}
	}
}
