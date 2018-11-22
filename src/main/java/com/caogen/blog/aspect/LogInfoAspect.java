package com.caogen.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.caogen.blog.annotation.LogInfoAnno;
import com.caogen.blog.dto.LogInfo;
import com.caogen.blog.service.LogInfoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class LogInfoAspect {

    @Autowired
    private LogInfoService logInfoService;

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(com.caogen.blog.annotation.LogInfoAnno)")
    public void logPointCut() {

    }

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        try {
            saveLog(point, time);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogInfo logInfo = new LogInfo();
        logInfo.setExeuTime(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logInfo.setCreateDate(dateFormat.format(new Date()));
        LogInfoAnno logInfoAnno = method.getAnnotation(LogInfoAnno.class);
        if (logInfoAnno != null) {
            //注解上的描述
            logInfo.setRemark(logInfoAnno.value());
        }
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logInfo.setClassName(className);
        logInfo.setMethodName(methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = new ArrayList<>();
            for (Object o : args) {
                list.add(JSON.toJSONString(o));
            }
            logInfo.setParams(list.toString());
        } catch (Exception e) {
        }
        logInfoService.save(logInfo);
    }


}
