package io.github.lingalone.springbootaopdemo.exe;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/6/22
 */

public abstract class ExeBody<T> {

    private T config;

    public ExeBody(T config) {
        this.config = config;
    }

    public abstract void exe();

}
