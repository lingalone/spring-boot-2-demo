package io.github.lingalone.springbootaopdemo.interceptor;

import io.github.lingalone.springbootaopdemo.annotation.ExcLog;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link http://www.shouce.ren/api/spring2.5/ch07s03.html
 * @link https://my.oschina.net/u/1169457/blog/1489113
 * @link http://blog.javaforge.net/post/76125490725/spring-aop-method-interceptor-annotation
 * @since 2018/6/21
 */
@Component
public class ExcLogInterceptor implements HandlerInterceptor{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ExcLog excLog = method.getAnnotation(ExcLog.class);
        //判断当前注解是否存在
        if(excLog != null){
            long startTime = System.currentTimeMillis();
            httpServletRequest.setAttribute("startTime",startTime);
            logger.info("进入" + method.getName() + "方法的时间是：" + startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ExcLog excLog = method.getAnnotation(ExcLog.class);
        //判断当前注解是否存在
        if(excLog != null){
            long endTime = System.currentTimeMillis();
            long startTime = (Long) httpServletRequest.getAttribute("startTime");
            long periodTime = endTime - startTime;
            logger.info("离开" + method.getName() + "方法的时间是：" + endTime);
            logger.info("在" + method.getName() + "方法的时长是：" + periodTime);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
