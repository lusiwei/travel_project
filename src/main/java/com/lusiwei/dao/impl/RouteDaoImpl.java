package com.lusiwei.dao.impl;

import com.lusiwei.dao.RouteDao;
import com.lusiwei.pojo.Route;
import org.xmcc.jdbc.dao.BaseDao;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/13
 *
 * @author lusiwei
 */
public class RouteDaoImpl extends BaseDao<Route> implements RouteDao {
    @Override
    public List<Route> queryRouteByCid(Connection connection, Integer cid,int t1,int t2) {
        String sql = "select * from tab_route where cid=? limit "+t1+","+t2;
        List<Route> routeList = super.queryList(connection, Route.class, sql, cid);
        return routeList;
    }

    @Override
    public long queryCount(Connection connection, Integer cid) {
        String sql = "select count(*) from tab_route where cid=?";
        long l = super.queryCount(connection, sql, cid);
        return l;
    }
}
