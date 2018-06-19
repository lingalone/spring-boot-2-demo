package io.github.lingalone.webdevdemo.controller.api;

import com.github.pagehelper.PageHelper;

import io.github.lingalone.webdevdemo.common.Page;
import io.github.lingalone.webdevdemo.common.PageTransfer;
import io.github.lingalone.webdevdemo.common.ReturnMsg;
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
@RestController
@RequestMapping("/api/v1/test")
public class TestApiController {

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
        return "Hello World -> " + System.currentTimeMillis();
    }


    @PostMapping(value = "/change")
    public ReturnMsg<TestDomain> changeTest(@Valid TestDomain test, BindingResult bindingResult){
        ReturnMsg<TestDomain> res = new ReturnMsg<>();
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            res.setRequestStatus(RequestStatus.PARAMSVALID);
            res.setRemark(msg);

        }else{
            test.setContext("has change test ..");
            res.setRequestStatus(RequestStatus.SUCCESS);
            res.setData(test);
        }
        logger.info("res:{}", res.toString());
        return res;
    }


    @GetMapping(value = "/list")
    public ReturnMsg<Page<TestDomain>> list(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){

        ReturnMsg<Page<TestDomain>> res = new ReturnMsg<>();

        PageHelper.startPage(page, size);
        List result =  testDomainMapper.selectAll();
        res.setRequestStatus(RequestStatus.SUCCESS);
        res.setData(new PageTransfer<TestDomain>().toPage(result));
        logger.info("res:{}", res.toString());
        return res;
    }


    @GetMapping(value = "add_all")
    public ReturnMsg<String> addAll(){
        ReturnMsg<String> res = new ReturnMsg<>();
        res.setRequestStatus(RequestStatus.SUCCESS);
        int i =100;
        while(i-->0){
            TestDomain domain = new TestDomain();
            domain.setId(UUID.randomUUID().toString());
            domain.setContext(UUID.randomUUID().toString());
            testDomainMapper.insert(domain);
        }
        return res;
    }

    @GetMapping(value = "get")
    @Cacheable(cacheNames  = "testDomain" , key = "#id")
    public ReturnMsg<TestDomain> get(@RequestParam String id){
        ReturnMsg<TestDomain> res = new ReturnMsg<>();
        res.setRequestStatus(RequestStatus.SUCCESS);
        TestDomain domain = new TestDomain();
        domain.setId(id);
        res.setData(testDomainMapper.selectOne(domain));
        return res;
    }


}
