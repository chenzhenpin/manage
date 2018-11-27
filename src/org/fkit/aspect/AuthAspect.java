package org.fkit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;


@Aspect
public class AuthAspect {
	//前面的*号要加空格
	@Before("execution(* org.fkit.service.impl.*.*(..))")
	public void authority(){
		System.out.println("aop Before前处理");
	}
	//@@AfterReturning原始方法执行有错误不能执行到
	@AfterReturning(returning="rvt",pointcut="execution(* org.fkit.service.impl.*.*(..))")
	public void afterreturn(Object rvt){
		System.out.println("aop AfterReturning增强后处理");
		//rvt是原始方法执行的返回值
		System.out.println(rvt);
	}
	//@After原始方法执行是否有错误都会执行到
	@After("execution(* org.fkit.service.impl.*.*(..))")
	public void after(){
		System.out.println("aop After后处理");
	
	}
	@Around("execution(* org.fkit.service.impl.*.*(..))")
	public Object around(ProceedingJoinPoint jp) throws java.lang.Throwable{
		System.out.println("aop around处理前");

		Object[] args=jp.getArgs();
		Object ob = null;
		if(args!=null && args.length>0){
			//调用原始方法，有返回值可以获取返回值Object rvt=jp.proceed(args),不调用jp.proceed(args)原始方法不会执行。
			ob=jp.proceed(args);
		}else{
			ob=jp.proceed();
		}
		System.out.println("aop around处理后");
		return ob;
	}
}
