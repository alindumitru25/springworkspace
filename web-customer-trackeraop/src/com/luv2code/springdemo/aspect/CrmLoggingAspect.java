package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrmLoggingAspect {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
		
	}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		// display method
		String method = joinPoint.getSignature().toShortString();
		logger.info("---->>>> in @Before: calling method: " + method);
		
		//display arguments
		Object[] args = joinPoint.getArgs();
		
		for (Object arg: args) {
			logger.info("----->>>> argument: " + arg);
		}
	}
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		// display method
		String method = joinPoint.getSignature().toShortString();
		logger.info("---->>>> in @After: calling method: " + method);
		
		// display the data returned
		logger.info("----->>>>>> in @After return objjjj: " + result);
	}
}
