package ru.bunkov.market.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AOPStatistic {

    private Map<Class<?>, Long> timeMap;

    @PostConstruct
    private void postInit(){
        this.timeMap = new HashMap<>();
    }

    private void addTime(Class<?> currentClass, long time){
        long totalTime = timeMap.getOrDefault(currentClass, 0L) + time;
        timeMap.put(currentClass, totalTime);
    }

    @Around("execution(public * ru.bunkov.market.services.*.*(..))")
    public Object serviceProfiling(ProceedingJoinPoint joinPoint)throws Throwable{
        long startTime = System.currentTimeMillis();
        Object out = joinPoint.proceed();
        long currentTime = System.currentTimeMillis() - startTime;
        Class<?> source = joinPoint.getSignature().getDeclaringType();
        addTime(source, currentTime);
        return out;
    }

    public Map<String, Long> getTimeStatistic(){
        Map<String,Long> statisticMap = new HashMap<>();
        timeMap.forEach((currentClass,totalTime)-> statisticMap.put(currentClass.getName(), totalTime));
        return statisticMap;
    }
}
