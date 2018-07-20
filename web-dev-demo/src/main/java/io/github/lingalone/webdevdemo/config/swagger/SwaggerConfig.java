package io.github.lingalone.webdevdemo.config.swagger;


import io.github.lingalone.webdevdemo.config.swagger.SwaggerProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.support.GenericWebApplicationContext;
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


    public SwaggerConfig(SwaggerProperties properties, GenericWebApplicationContext applicationContext){
        System.out.println(properties.toString());
        properties.getVersions().forEach( version -> {
            ApiInfo apiInfo = new ApiInfoBuilder()
                    .title(version.getTitle())
                    .description(version.getDescription())
                    .contact(new Contact(
                            version.getContactName(),
                            version.getContactUrl(),
                            version.getContactEmail()))
                    .version(version.getVersion())
                    .license(version.getLicense())
                    .licenseUrl(version.getLicenseUrl())
                    .termsOfServiceUrl(version.getTermsOfServiceUrl())
                    .build();

            try{

                applicationContext.registerBean("docket_"+version.getVersion() , Docket.class, () -> new Docket(DocumentationType.SWAGGER_2)
                        .groupName(version.getGroupName())
                        .apiInfo(apiInfo)
                        .select()
                        .apis(StringUtils.isBlank(version.getApis()) ? RequestHandlerSelectors.any() : RequestHandlerSelectors.basePackage(version.getApis()))
                        .paths(regex(version.getRegex()))
                        .build());

            }catch (Exception e){
                e.printStackTrace();
            }

        });

    }


}
