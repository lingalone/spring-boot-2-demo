package io.github.lingalone.webdevdemo.controller.api.v2;

import io.github.lingalone.webdevdemo.config.apiversion.ApiVersion;
import io.github.lingalone.webdevdemo.mapper.TestDomainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/3/30
 */

@RequestMapping("/api/v2/test")
@RestController("VersionTestApiController-v2")
public class VersionTestApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    TestDomainMapper testDomainMapper;


    @RequestMapping(
            value = "/hello" ,
            method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = MediaType.ALL_VALUE
    )
    public String hello() {
        return "Hello World v2-> " + System.currentTimeMillis();
    }




}
