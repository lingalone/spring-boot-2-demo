package io.github.lingalone.webdevdemo.config.apiversion;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/7/19
 */

public class ApiVersioningRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }

    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }


    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = this.createRequestMappingInfo(method, null);
        if (info != null) {
            ApiVersion apiVersion = AnnotatedElementUtils.findMergedAnnotation(method, ApiVersion.class);
            RequestMappingInfo typeInfo = this.createRequestMappingInfo(handlerType, apiVersion);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }
        }
        return info;
    }
    private RequestMappingInfo createRequestMappingInfo(AnnotatedElement element, ApiVersion apiVersion) {
        RequestMapping requestMapping = (RequestMapping) AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        RequestCondition<?> condition = element instanceof Class ? this.getCustomTypeCondition((Class)element) : this.getCustomMethodCondition((Method)element);
        if (element instanceof Class && null != apiVersion) {
            try {
                // 动态修改RequestMapping注解的属性
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(requestMapping);
                Field field = invocationHandler.getClass().getDeclaredField("valueCache");
                // SynthesizedAnnotationInvocationHandler的valueCache是私有变量，需要打开权限
                field.setAccessible(true);
                Map map = (Map) field.get(invocationHandler);
                String[] paths = new String[requestMapping.path().length];
                for (int i = 0; i< requestMapping.path().length; i++) {
                    paths[i] = requestMapping.path()[i].replace("{version}", "v"+apiVersion.value());
                }
                map.put("path", paths);
                String[] values = new String[requestMapping.value().length];
                for (int i = 0; i< requestMapping.value().length; i++) {
                    values[i] = requestMapping.value()[i].replace("{version}", "v"+apiVersion.value());
                }
                map.put("value", values);
                // 上面改了value和path是因为注解里@AliasFor，两者互为，不晓得其它地方有没有用到，所以都改了，以免其它问题
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return requestMapping != null ? this.createRequestMappingInfo(requestMapping, condition) : null;
    }

}
