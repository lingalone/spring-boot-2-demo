package io.github.lingalone.webdevdemo.common;

import lombok.Data;

/**
 * 分页参数
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/3/30
 */
@Data
public class Pagination {

    private int     pages;              //页总数
    private long    total;              //条总数
    private int     limit;              //页条数
    private int     current;            //当前页
}
