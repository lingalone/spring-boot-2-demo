package io.github.lingalone.webdevdemo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/5/8
 */

@ConfigurationProperties(prefix = "common.swagger", ignoreUnknownFields = false)
@Data
public class SwaggerProperties {

    private String title = "Application API";

    private String description = "API documentation";

    private String version = "0.0.1";

    private String termsOfServiceUrl = "";

    private Contact contact = new Contact();

    private String license = "";

    private String licenseUrl = "";

    private String regex = "/api/.*";

    private String apis = "";

    @Data
    public class Contact{
        private String name = "";
        private String url = "";
        private String email = "";

    }


}
