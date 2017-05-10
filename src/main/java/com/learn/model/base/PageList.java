package com.learn.model.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/9
 * Time: 下午9:58
 */
public class PageList<T> implements Serializable {

    private List<T> list;
    private int total;

    public PageList(List<T> list, int total) {
        this.list = list;
        this.total = total;
    }

    public PageList() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
