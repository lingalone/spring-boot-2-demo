package io.github.lingalone.springbootaopdemo.aspect;

import io.github.lingalone.springbootaopdemo.annotation.ExcServiceLog;
import io.github.lingalone.springbootaopdemo.exe.ExeBody;
import io.github.lingalone.springbootaopdemo.exe.ExeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link http://www.baeldung.com/spring-aop-pointcut-tutorial
 * @since 2018/6/22
 */

@Aspect
@Component
public class ExcLogAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ExeService exeService;

    @Pointcut("@annotation(io.github.lingalone.springbootaopdemo.annotation.ExcServiceLog)")
    public void excServiceLog(){}










    @Around("excServiceLog()")
    public void serviceLogAround(ProceedingJoinPoint proceedingJoinPoint){

        try {
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = signature.getMethod();

            logger.info("method name -> {}",method.getName());
            ExcServiceLog serviceLog = method.getAnnotation(ExcServiceLog.class);
            logger.info("annotation name -> {}",serviceLog.name());
            logger.info("around start -> {}");
            proceedingJoinPoint.proceed();
            logger.info("around end -> {}");


            String config = serviceLog.name();
            ExeBody<String> excBody= new ExeBody<String>(config) {
                @Override
                public void exe() {
                    logger.info("exe start-> {}, {}", config, System.currentTimeMillis());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("exe end  -> {}, {}", config, System.currentTimeMillis());
                }
            };
            exeService.addTask(excBody);


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



}
