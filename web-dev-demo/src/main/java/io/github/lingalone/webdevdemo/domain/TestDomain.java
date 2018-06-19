package io.github.lingalone.webdevdemo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/3/30
 */

@Data
public class TestDomain {

    @NotNull(message = " TestDomain.id cannot be null")
    private String id;
    @NotNull(message = " TestDomain.context cannot be null")
    private String context;
}
