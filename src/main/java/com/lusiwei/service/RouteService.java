package com.lusiwei.service;

/**
 * Created  by lusiwei on 2018/10/13
 * @author lusiwei
 */
public interface RouteService {
    /**
     *
      * @param cid
     * @param curPage
     * @return String
     * 通过controller层传过来的cid去调用dao层的方法查询
     */
    String queryRouteByCid(Integer cid,int curPage);
}
