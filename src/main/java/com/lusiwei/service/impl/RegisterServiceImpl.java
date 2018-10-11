package com.lusiwei.service.impl;

import com.lusiwei.dao.impl.UserDaoImpl;
import com.lusiwei.pojo.User;
import com.lusiwei.service.RegisterService;
import com.lusiwei.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created  by lusiwei on 2018/10/11
 * @author lusiwei
 */
public class RegisterServiceImpl implements RegisterService {
    private UserDaoImpl userDao = new UserDaoImpl();

    public boolean checkUserName(String username) {
        Connection connection=null;
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = userDao.queryUserName(connection, username);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    }
}
