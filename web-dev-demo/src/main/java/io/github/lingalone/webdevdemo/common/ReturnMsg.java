package io.github.lingalone.webdevdemo.common;


import io.github.lingalone.webdevdemo.constanst.RequestStatus;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 返回信息体
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/3/30
 */

@Data
public class ReturnMsg<T>{

    private Integer code;
    private String  msg;
    private String  remark;
    private T       data;

    public void setRequestStatus(RequestStatus status){
        this.code = status.getCode();
        this.msg  = status.getMsg();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
