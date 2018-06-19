package io.github.lingalone.webdevdemo.controller.error;


import io.github.lingalone.webdevdemo.common.ReturnMsg;
import io.github.lingalone.webdevdemo.constanst.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/5/10
 */

@RestControllerAdvice("io.github.lingalone.tkservice")
public class ErrorRestControllerAdvice {





    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnMsg<String> ex(Exception e){
        ReturnMsg<String> returnMsg = new ReturnMsg<>();
        returnMsg.setRequestStatus(RequestStatus.PARAMSVALID);
        return returnMsg;

    }
}
