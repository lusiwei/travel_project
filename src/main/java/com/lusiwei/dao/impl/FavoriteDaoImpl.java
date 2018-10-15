package com.lusiwei.dao.impl;

import com.lusiwei.dao.FavoriteDao;
import com.lusiwei.pojo.Route;
import org.xmcc.jdbc.dao.BaseDao;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/14
 *
 * @author lusiwei
 */
public class FavoriteDaoImpl extends BaseDao implements FavoriteDao {
    @Override
    public int addFavorite(Connection connection, int rid, int uid, String dateString) {
        String sql = "insert into tab_favorite values(?,?,?)";
        int update;
        update = super.update(connection, sql, rid, dateString, uid);
        return update;
    }

    @Override
    public List<Route> queryMyFavorite(Connection connection, Integer uid) {
        String sql = "select * from tab_route where rid in (select rid from tab_favorite where uid=?)";
        List<Route> list = super.queryList(connection, Route.class, sql, uid);
        return list;
    }
}
