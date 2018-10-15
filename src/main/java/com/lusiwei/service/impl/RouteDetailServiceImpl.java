package com.lusiwei.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lusiwei.dao.RouteDetailDao;
import com.lusiwei.dao.impl.RouteDetailDaoImpl;
import com.lusiwei.pojo.Category;
import com.lusiwei.pojo.Route;
import com.lusiwei.pojo.RouteImg;
import com.lusiwei.pojo.Seller;
import com.lusiwei.service.RouteDetailService;
import com.lusiwei.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/14
 *
 * @author lusiwei
 */
public class RouteDetailServiceImpl implements RouteDetailService {
    private RouteDetailDao routeDetailDao = new RouteDetailDaoImpl();
    private ObjectMapper objectMapper = new ObjectMapper();
    private Connection connection = null;
    private String resultString = null;


    @Override
    public String queryRouteByRid(int rid, int uid) {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //查询route
        Route routeList = routeDetailDao.queryRouteByRid(connection, rid);
        //查询 分类
        Category category = routeDetailDao.queryByCid(connection, routeList.getCid());
        //设置category
        routeList.setCategory(category);
        //查询图片
        List<RouteImg> routeImgList = routeDetailDao.queryImgByRid(connection, routeList.getRid());
        //设置图片
        routeList.setRouteImgList(routeImgList);
        //查询商家
        Seller seller = routeDetailDao.querySeller(connection, routeList.getSid());
        //设置商家
        routeList.setSeller(seller);
        //查询收藏次数
        long favoriteCount = routeDetailDao.queryFavoriteCount(connection, routeList.getRid());
        //设置收藏次数
        routeList.setCount((int) favoriteCount);
        //是否收藏
        long l = routeDetailDao.queryIsFavorite(connection, routeList.getRid(), uid);
        if (l > 0) {
            routeList.setFavorite(true);
        } else {
            routeList.setFavorite(false);
        }
        try {
            resultString = objectMapper.writeValueAsString(routeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JDBCUtils.close(connection, null);
        return resultString;
    }
}
