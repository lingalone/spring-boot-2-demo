package io.github.lingalone.webdevdemo.config.apiversion;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;



/*https://www.hifreud.com/2018/01/30/01-API-versioning/*/
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * version
     *
     * @return
     */
    int value();
}