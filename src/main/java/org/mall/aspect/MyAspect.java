package org.mall.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class MyAspect {

    @Pointcut("execution(* org.mall.service.impl.CommodityServiceImpl.findAll(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("........before........");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("........after........");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("........afterReturning..........");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("........afterThrowing..........");
    }
}
