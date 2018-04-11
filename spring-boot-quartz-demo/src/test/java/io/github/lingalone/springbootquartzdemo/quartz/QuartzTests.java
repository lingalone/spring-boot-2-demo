package io.github.lingalone.springbootquartzdemo.quartz;

import org.junit.Test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/4/9
 */

public class QuartzTests {



//    @Test
    public static void quartzTest() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(QuartzTestJob.class)
                .withIdentity("testjob","test")
                .usingJobData("test","hello world !")
                .build();


        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger","test")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever()
                )
                .build();


        scheduler.scheduleJob(jobDetail, trigger);

    }


    public static void main(String[] args) {
        try {
            QuartzTests.quartzTest();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
