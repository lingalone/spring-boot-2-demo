package io.github.lingalone.springboot2feature.functional_bean_registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.GenericWebApplicationContext;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BeanRegistrationIntegrationTest {

    @Autowired
    private GenericWebApplicationContext context;
    @Test
    public void test(){
        context.registerBean("testService1",
                TestService.class,
                () -> new TestService(),
                beanDefinition -> beanDefinition.setAutowireCandidate(false));

        TestService testService1 = (TestService) context.getBean("testService1");
        assertTrue(testService1.getRandomNumber() < 10);
    }

}