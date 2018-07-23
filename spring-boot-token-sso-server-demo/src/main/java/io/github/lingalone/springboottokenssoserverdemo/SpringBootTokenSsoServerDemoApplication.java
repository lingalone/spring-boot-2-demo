package io.github.lingalone.springboottokenssoserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SpringBootTokenSsoServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTokenSsoServerDemoApplication.class, args);
	}
}
