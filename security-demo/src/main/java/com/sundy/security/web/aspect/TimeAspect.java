package com.sundy.security.web.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class TimeAspect {


    @Around("execution(* com.sundy.security.web.controller.UserController.*(..))")
    public Object handleController(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("time aspect start");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }
        Object object = point.proceed();
        stopWatch.stop();
        System.out.println("time aspect 耗时:" + stopWatch.getTotalTimeMillis());
        System.out.println("time aspect end");
        return object;
    }

}
