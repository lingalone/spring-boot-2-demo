package io.github.lingalone.webdevdemo.config;


import io.github.lingalone.webdevdemo.property.SwaggerProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/5/8
 */

@EnableSwagger2
@Configuration
@Profile("swagger")
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfig {

    @Bean
    public Docket buildDocket(SwaggerProperties properties) {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .contact(new Contact(
                        properties.getContact().getName(),
                        properties.getContact().getUrl(),
                        properties.getContact().getEmail()))
                .version(properties.getVersion())
                .license(properties.getLicense())
                .licenseUrl(properties.getLicenseUrl())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .build();


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(StringUtils.isBlank(properties.getApis()) ? RequestHandlerSelectors.any() : RequestHandlerSelectors.basePackage(properties.getApis()))
                .paths(regex(properties.getRegex()))
                .build();
    }


}
