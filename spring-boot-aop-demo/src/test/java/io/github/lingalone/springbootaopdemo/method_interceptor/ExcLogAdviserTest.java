package io.github.lingalone.springbootaopdemo.method_interceptor;

import io.github.lingalone.springbootaopdemo.service.ExcLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcLogAdviserTest {


    @Autowired
    ExcLogService excLogService;

    @Test
    public void test(){
        excLogService.test();
    }

}