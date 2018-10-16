package com.lusiwei.dao;

import com.lusiwei.pojo.Route;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/15
 */
public interface SearchDao {
    /**
     * @param connection
     * @param keyword
     * @param t1
     * @param t2
     * @return List<Route>
     */
    List<Route> queryRoutes(Connection connection, String keyword,int t1,int t2);

    /**
     * @param connection
     * @param keyword
     * @return
     */
    long queryCount(Connection connection, String keyword);
}
