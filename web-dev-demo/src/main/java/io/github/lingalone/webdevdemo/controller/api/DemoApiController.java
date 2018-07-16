package io.github.lingalone.webdevdemo.controller.api;


import io.github.lingalone.webdevdemo.domain.Demo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/5/8
 */


@Api(value = "/demo", description = "Operations about demo",tags = "Demo-Demo", position=0)
@RestController
@RequestMapping("/api/v1/demo")
public class DemoApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @ApiOperation(
            value = "get-test",
            notes = "",
            response = Demo.class
    )
    @GetMapping(value = {"","/test"})
    public Demo getDemo(Demo demo) {
        logger.debug("..........xxxx................");
        return new Demo();
    }

    @ApiOperation(value = "post-test", notes = "")
    @PostMapping(value = {"","/test"})
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "demo",
                    dataType = "Demo",
                    examples = @io.swagger.annotations.Example(
                            value = {
                                    @ExampleProperty(value = "{'property': 'test'}", mediaType = "application/json")
                            }))
    })
    public String postDemo(@RequestBody Demo demo) {
        logger.debug("..........xxxx................");
        return "test : hello world !";
    }

}
