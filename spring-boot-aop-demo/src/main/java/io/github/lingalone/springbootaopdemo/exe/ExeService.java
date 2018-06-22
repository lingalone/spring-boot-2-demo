package io.github.lingalone.springbootaopdemo.exe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 通用的异步线程池
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/6/22
 */
@Scope(scopeName = "singleton")
@Service
public class ExeService {

    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${exe.task.thread.num:5}")
    private Integer threadNum = 5;

    @Value("${empty.task.sleep.time:20}")
    private Integer sleepTime = 20;


    private LinkedBlockingQueue<ExeTask> tasks = new LinkedBlockingQueue<>();
    private ExecutorService executorService = null;

    @PostConstruct
    protected void init() {
        executorService = Executors.newFixedThreadPool(threadNum);

        new Thread(() -> {
            while (true) {
                try {
                    ExeTask task = tasks.poll();
                    if (task == null) {
                        TimeUnit.SECONDS.sleep(sleepTime);
                        logger.debug("wait for task ->{}", System.currentTimeMillis());
                        continue;
                    }
                    executorService.execute(task);

                } catch (Exception e) {
                    logger.error("exe task error", e);
                }
            }
        }).start();
    }

    public Boolean addTask(ExeBody exeBody){
        ExeTask task = new ExeTask(exeBody);
        this.tasks.offer(task);
        return true;
    }


    public void demo(){

        String config = "hello world !";
        ExeBody<String> excBody= new ExeBody<String>(config) {
            @Override
            public void exe() {
                System.out.println(config);
            }
        };

        ExeTask task = new ExeTask(excBody);
    }
}
