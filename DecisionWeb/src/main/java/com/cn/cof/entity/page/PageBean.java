package com.cn.cof.entity.page;

import java.util.List;

/**
 * 分页JavaBean
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/15 17:13
 */
public class PageBean<T> {
    /*当前页的记录列表*/
    private List<T> list;
    /*总记录数*/
    private int totalSize;
    /*总页数*/
    private int totalPage;
    /*当前页*/
    private int currentPage;
    /*每页记录数*/
    private int pageSize;

    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 是否是第一页
     *
     * @return Boolean
     */
    public boolean isFirstPage() {
        return currentPage == 1;
    }

    /**
     * 是否是最后一页
     *
     * @return Boolean
     */
    public boolean isLastPage() {
        return currentPage == totalPage;
    }

    /**
     * 是否有上一页
     *
     * @return Boolean
     */
    public boolean isHasPreviousPage() {
        return currentPage != 1;
    }

    /**
     * 是否有下一页
     *
     * @return Boolean
     */
    public boolean isHasNextPage() {
        return currentPage != totalPage;
    }

    /**
     * 初始化分页信息
     */
    public void init() {
        isFirstPage = isFirstPage();
        isLastPage = isLastPage();
        hasPreviousPage = isHasPreviousPage();
        hasNextPage = isHasNextPage();
    }

    public PageBean() {
    }

    public PageBean(List<T> list, int totalSize, int totalPage, int currentPage, int pageSize) {
        this.list = list;
        this.totalSize = totalSize;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.init();
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "list=" + list +
                ", totalSize=" + totalSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                '}';
    }
}
