package io.github.lingalone.webdevdemo.common;

import lombok.Data;

import java.util.List;

/**
 * 分页内容结构
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/3/30
 */
@Data
public class Page<T> {
    private Pagination  pagination;         //分页信息
    private List<T>     result;             //本页数据
}
