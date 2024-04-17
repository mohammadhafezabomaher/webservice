package com.example.pidev.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.pidev.controllers.ProduitController.addProduit(..))")
    public void logAddProduitMethodCall(JoinPoint joinPoint) {
        logger.info("Calling addProduit method");
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            logger.info("Arguments: " + Arrays.toString(args));
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.pidev.controllers.ProduitController.addProduit(..))",
            returning = "result")
    public void logAddProduitMethodReturn(JoinPoint joinPoint, Object result) {
        logger.info("addProduit method returned: " + result);
    }
}
