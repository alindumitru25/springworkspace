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
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	// combine pointcut to exclude setter & getter
	@Pointcut("forDAOPackage() && !(getter() || setter())")
	private void forDAOPackageWithoutGetterAndSetter() {}
	
	// @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
	@Before("forDAOPackageWithoutGetterAndSetter()")
	public void beforeAddReturnString() {
		System.out.println("\n======>> Executing @Before advice on addAccount() & returns\n");
	}
	
	@Before("forDAOPackageWithoutGetterAndSetter()")
	public void performApiAnalytics() {
		System.out.println("\n======>> Perform API analytics\n");
	}
}
