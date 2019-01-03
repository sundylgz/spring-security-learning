package com.sundy.security.validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
public class ValidateAspect {


    @Around("execution(* com.sundy.security.web.controller.UserController.*(..))")
    public Object handleValidateResult(ProceedingJoinPoint point) throws Throwable {
        System.out.println("进入切片");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult errors = (BindingResult) arg;
                if (errors.hasErrors()) {
                    System.out.println(errors.getModel());
                    throw new ValidateException(errors.getAllErrors());
                }
            }
        }
        Object result = point.proceed();
        return result;
    }

}
