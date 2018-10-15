package com.lusiwei.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lusiwei.dao.FavoriteDao;
import com.lusiwei.dao.impl.FavoriteDaoImpl;
import com.lusiwei.pojo.Route;
import com.lusiwei.service.FavoriteService;
import com.lusiwei.util.DateUtil;
import com.lusiwei.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/14
 *
 * @author lusiwei
 */
public class FavoriteServiceImpl implements FavoriteService {
    private Connection connection = null;
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private String dateString;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean addFavorite(int rid, int uid) {
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //当前时间
        dateString = DateUtil.dateFormat(new Date());
        int b = favoriteDao.addFavorite(connection, rid, uid, dateString);
        if (b > 0) {
            try {
                connection.commit();
            } catch (SQLException e) {
                System.out.println("已经收藏了");
                e.printStackTrace();
            }
            return true;
        } else {
            try {
                connection.rollback();
                JDBCUtils.close(connection, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private String resultString = null;

    @Override
    public String queryMyFavorite(Integer uid) {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Route> routeList = favoriteDao.queryMyFavorite(connection, uid);
        try {
            resultString = objectMapper.writeValueAsString(routeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JDBCUtils.close(connection, null);
        return resultString;
    }
}
