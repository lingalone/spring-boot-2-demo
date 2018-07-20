package io.github.lingalone.webdevdemo.controller.api.v1;

import com.github.pagehelper.PageHelper;
import io.github.lingalone.webdevdemo.common.Page;
import io.github.lingalone.webdevdemo.common.PageTransfer;
import io.github.lingalone.webdevdemo.common.ReturnMsg;
import io.github.lingalone.webdevdemo.config.apiversion.ApiVersion;
import io.github.lingalone.webdevdemo.constanst.RequestStatus;
import io.github.lingalone.webdevdemo.domain.TestDomain;
import io.github.lingalone.webdevdemo.mapper.TestDomainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/3/30
 */
@ApiVersion(1)
@RequestMapping("/api/{api_version}/test")
@RestController("VersionTestApiController-v1")
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
        return "Hello World v1-> " + System.currentTimeMillis();
    }




}
