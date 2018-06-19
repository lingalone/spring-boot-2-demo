package io.github.lingalone.webdevdemo.domain;

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
public class Demo {

    private String a = UUID.randomUUID().toString();
    private String b = UUID.randomUUID().toString();
    private String c = UUID.randomUUID().toString();
    private String d = UUID.randomUUID().toString();
    private String e = UUID.randomUUID().toString();
    private String f = UUID.randomUUID().toString();
    private Boolean ddd = true;
}
