package com.swx.store1.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * Author: Admin
 * Date: 2021/11/14 12:12
 * FileName: TimerAspect
 * Description:1.切面方法修饰符必须是public。
 * 2.切面方法的返回值可以是void和Object。如果这个方法被@Around注解修饰此方法必须声明为Object类型，反之随意。
 * 3.切面方法的方法名称可以自定义。
 * 4.切面方法可以接收参数，参数是ProceedingJoinPoint接口类型的参数。但是@Aroud所修饰方法必须要传递这个参数，其他随意。
 * <p>
 * aop不是spring内部技术，只是spring很好的支持了aop，因此需要导包
 */
@Component//将当前类交给spring容器管理
@Aspect //将当前类标志为切面类
public class TimerAspect {
    //最后两个.*.*表示包下所有类，所有类的所有方法，(..)表示任意参数
    @Around("execution(* com.swx.store1.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.err.println("耗时："+(end - start));
        System.out.println(proceed);
        return proceed;
    }

}
