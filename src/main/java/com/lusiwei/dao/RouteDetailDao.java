package com.lusiwei.dao;

import com.lusiwei.pojo.Category;
import com.lusiwei.pojo.Route;
import com.lusiwei.pojo.RouteImg;
import com.lusiwei.pojo.Seller;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/14
 * @author lusiwei
 */
public interface RouteDetailDao {

    Route queryRouteByRid(Connection connection, int rid);

    Category queryByCid(Connection connection, Integer cid);

    List<RouteImg> queryImgByRid(Connection connection, Integer rid);

    Seller querySeller(Connection connection, Integer sid);

    long queryFavoriteCount(Connection connection, Integer rid);

    long queryIsFavorite(Connection connection, Integer rid, int uid);
}
