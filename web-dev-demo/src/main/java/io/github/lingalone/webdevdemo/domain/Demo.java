package io.github.lingalone.webdevdemo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/5/8
 */

@Data
@ApiModel
public class Demo {

    @ApiModelProperty(value = "错误码-A", required = true, position = 0)
    private String a = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-B", required = true, position = 1)
    private String b = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-C", required = true, position = 2)
    private String c = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-D", required = true, position = 3)
    private String d = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-E", required = true, position = 4)
    private String e = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-F", required = true, position = 5)
    private String f = UUID.randomUUID().toString();
    private Boolean ddd = true;
}
