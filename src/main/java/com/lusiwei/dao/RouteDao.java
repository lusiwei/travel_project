package com.lusiwei.dao;

import com.lusiwei.pojo.Route;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/13
 *
 * @author lusiwei
 */
public interface RouteDao {
    /**
     * 根据cid 和t1 t2查询单页数据
     * @param connection
     * @param cid
     * @param t1
     * @param t2
     * @return
     */
    List<Route> queryRouteByCid(Connection connection, Integer cid, int t1, int t2);

    /**
     * 查询数据条数
     * @param connection
     * @param cid
     * @return
     *
     */
    long queryCount(Connection connection, Integer cid);

    List<Route> queryRouteByRid(Connection connection, int rid);
}
