package com.lusiwei.dto;

import java.util.List;

/**
 * Created  by lusiwei on 2018/10/13
 * @author lusiwei
 */
public class PageUtil<E> {
    /**当前页*/
    private Integer curPage;
    /**总页数*/
    private Long pageCount;
    /**总条数*/
    private Long totalCount;
    /**每页条数*/
    private Integer pageSize=10;
    private String recentRoute;
    private List<E> dataList;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public long getPageCount() {
        return totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<E> getDataList() {
        return dataList;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

    public String getRecentRoute() {
        return recentRoute;
    }

    public void setRecentRoute(String recentRoute) {
        this.recentRoute = recentRoute;
    }
}
