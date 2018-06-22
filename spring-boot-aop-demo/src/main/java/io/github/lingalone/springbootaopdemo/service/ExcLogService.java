package io.github.lingalone.springbootaopdemo.service;

import io.github.lingalone.springbootaopdemo.annotation.ExcServiceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/6/22
 */

@Service
public class ExcLogService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @ExcLog
    @ExcServiceLog(name = "ExcLogService")
    public int test(Integer ddddd){

        try {
            logger.info("test start. ->{}", ddddd);
            Thread.sleep(10000);
            logger.info("test start");
            return 100+ddddd;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0+ddddd;


    }

}
