package io.github.lingalone.springbootaopdemo.exe;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/6/22
 */

public class ExeTask implements Runnable {


    private ExeBody body;

    public ExeTask(ExeBody body) {
        this.body = body;
    }

    @Override
    public void run() {
        body.exe();
    }
}
