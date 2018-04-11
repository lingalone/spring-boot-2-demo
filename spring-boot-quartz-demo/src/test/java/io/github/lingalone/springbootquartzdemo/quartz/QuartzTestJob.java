package io.github.lingalone.springbootquartzdemo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/4/9
 */

public class QuartzTestJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("this is a quartz test job !");


        System.out.println("context : "+context.getJobDetail().getJobDataMap().getString("test"));
    }
}
