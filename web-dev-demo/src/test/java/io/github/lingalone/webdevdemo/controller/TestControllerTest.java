package io.github.lingalone.webdevdemo.controller;

import io.github.lingalone.webdevdemo.controller.api.TestApiController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTest {


    private Logger logger = LoggerFactory.getLogger(getClass());

    private MockMvc mvc;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new TestApiController()).build();
    }


    @Test
    public void hello() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/test/hello")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void changeTest() throws Exception {


        String content = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/test/change")
                .param("id","test")
                .param("context","test")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info("return : {}", content);
    }

    @Test
    public void list() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/test/list")
                .param("id","test")
                .param("context","test")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info("return : {}", content);
    }

}