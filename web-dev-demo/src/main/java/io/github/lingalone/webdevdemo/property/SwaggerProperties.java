package io.github.lingalone.webdevdemo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/5/8
 */

@ConfigurationProperties(prefix = "swagger", ignoreUnknownFields = false)
//@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {
    private List<SwaggerVersion> versions = new ArrayList<>();
}
