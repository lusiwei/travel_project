package com.lusiwei.dao.impl;

import com.lusiwei.dao.RouteDetailDao;
import com.lusiwei.pojo.Category;
import com.lusiwei.pojo.Route;
import com.lusiwei.pojo.RouteImg;
import com.lusiwei.pojo.Seller;
import org.xmcc.jdbc.dao.BaseDao;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/14
 * @author lusiwei
 */
public class RouteDetailDaoImpl extends BaseDao implements RouteDetailDao {

    @Override
    public Route queryRouteByRid(Connection connection, int rid) {
        String sql="select * from tab_route where rid=?";
        Route route = (Route) super.querySingle(connection, Route.class, sql, rid);
        return route;
    }

    @Override
    public Category queryByCid(Connection connection, Integer cid) {
        String sql = "select * from tab_category where cid=?";
        Category o = (Category)super.querySingle(connection, Category.class, sql, cid);
        return o;
    }

    @Override
    public List<RouteImg> queryImgByRid(Connection connection, Integer rid) {
        String sql="select * from tab_route_img where rid=?";
        List<RouteImg> routeImgList = super.queryList(connection, RouteImg.class, sql, rid);
        return routeImgList;
    }

    @Override
    public Seller querySeller(Connection connection, Integer sid) {
        String sql = "select * from tab_seller where sid=?";
        Seller seller=(Seller) super.querySingle(connection, Seller.class, sql, sid);
        return seller;
    }

    @Override
    public long queryFavoriteCount(Connection connection, Integer rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        long l = super.queryCount(connection, sql, rid);
        return l;
    }

    @Override
    public long queryIsFavorite(Connection connection, Integer rid, int uid) {
        String sql="select count(*) from tab_favorite where rid=? and uid=?";
        long l = super.queryCount(connection, sql, rid, uid);
        return l;

    }
}
