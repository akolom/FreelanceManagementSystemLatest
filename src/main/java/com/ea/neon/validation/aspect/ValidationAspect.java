package com.ea.neon.validation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author sabeen
 *
 */
@Aspect
	@Component
	public class ValidationAspect {
		
		@Pointcut("execution(* com.ea.neon.service..*(..))")
			public void lookForService(){}
		
		@Pointcut("args(..)")
		public void argsLookForService(){}
		
		@Pointcut("within(com.ea.neon.service..*)")
		public void withinLookForService(){}
		
		
		
		/**
		 * Advice for logging before the jointpoint stated
		 * @param joinPoint point where we want to run the advice
		 */
		@Before("argsLookForService() && withinLookForService()")
		public void logForServices(JoinPoint joinPoint){
		
			    System.out.println( "   **********     TARGET CLASS : " + 
				joinPoint.getSignature().getDeclaringTypeName() + "." +
				joinPoint.getSignature().getName() + 
					    			"    **********");
		}
	

}
