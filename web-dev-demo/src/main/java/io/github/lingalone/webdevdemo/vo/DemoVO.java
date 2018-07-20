package io.github.lingalone.webdevdemo.vo;

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
public class DemoVO {

    @ApiModelProperty(value = "错误码-A", required = true, position = 0, example="xxxxxx1")
    private String a = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-B", required = true, position = 1, example="xxxxxx2")
    private String b = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-C", required = true, position = 2, example="xxxxxx3")
    private String c = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-D", required = true, position = 3, example="xxxxxx4")
    private String d = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-E", required = true, position = 4, example="xxxxxx5")
    private String e = UUID.randomUUID().toString();
    @ApiModelProperty(value = "错误码-F", required = true, position = 5, example="xxxxxx6")
    private String f = UUID.randomUUID().toString();
    private Boolean ddd = true;
}
