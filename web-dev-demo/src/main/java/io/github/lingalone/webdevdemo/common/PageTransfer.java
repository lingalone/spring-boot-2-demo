package io.github.lingalone.webdevdemo.common;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/4/2
 */

public class PageTransfer<T> {

    public Page<T> toPage(List list){

        com.github.pagehelper.Page<T> tranTemp = (com.github.pagehelper.Page<T>) list;

        Pagination pagination = new Pagination();
        pagination.setCurrent(tranTemp.getPageNum());
        pagination.setLimit(tranTemp.getPageSize());
        pagination.setPages(tranTemp.getPages());
        pagination.setTotal(tranTemp.getTotal());
        Page<T> page = new Page<>();
        page.setResult(tranTemp.getResult());
        page.setPagination(pagination);
        return page;
    }

}
