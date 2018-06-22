package io.github.lingalone.springbootaopdemo.controller;

import io.github.lingalone.springbootaopdemo.annotation.ExcLog;
import io.github.lingalone.springbootaopdemo.service.ExcLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/6/22
 */


@RestController
public class ExcLogController {


    @Autowired
    ExcLogService excLogService;

    @RequestMapping("/test")
    @ExcLog
    public void test(){
        excLogService.test(111);
    }
}
