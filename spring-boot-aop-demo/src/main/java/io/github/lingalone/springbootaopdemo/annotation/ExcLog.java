package io.github.lingalone.springbootaopdemo.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link http://www.cnblogs.com/ycoe/archive/2009/09/26/1574565.html
 * @link https://joyrun.github.io/2016/07/18/java-annotation/
 * @since 2018/6/21
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcLog {
    public String name() default "";
}
