package com.lusiwei.dao.impl;

import com.lusiwei.dao.SearchDao;
import com.lusiwei.pojo.Route;
import org.xmcc.jdbc.dao.BaseDao;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/15
 * @author lusiwei
 */
public class SearchDaoImpl extends BaseDao implements SearchDao {
    @Override
    public List<Route> queryRoutes(Connection connection, String keyword,int t1,int t2) {
        String sql="select * from tab_route where rname like ? limit "+t1+","+t2;
        List<Route> list = super.queryList(connection, Route.class, sql, "%" + keyword + "%");
        return list;
    }

    @Override
    public long queryCount(Connection connection, String keyword) {
        String sql="select count(*) from tab_route where rname like ?";
        long l = super.queryCount(connection, sql, "%" + keyword + "%");
        return l;
    }
}
