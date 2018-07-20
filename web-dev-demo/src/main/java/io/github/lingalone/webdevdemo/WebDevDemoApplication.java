package io.github.lingalone.webdevdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan    //使Spring能够扫描到我们自己编写的servlet和filter
@MapperScan(basePackages = "io.github.lingalone.webdevdemo.mapper")
@EnableCaching
public class WebDevDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDevDemoApplication.class, args);
	}
}
