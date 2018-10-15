package com.lusiwei.dao;

import com.lusiwei.pojo.Route;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/14
 *
 * @author lusiwei
 */
public interface FavoriteDao {
    int addFavorite(Connection connection, int rid, int uid, String dateString);

    List<Route> queryMyFavorite(Connection connection, Integer uid);
}
