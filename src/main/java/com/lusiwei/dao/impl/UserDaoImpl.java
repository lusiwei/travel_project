package com.lusiwei.dao.impl;

import com.lusiwei.dao.UserDao;
import com.lusiwei.pojo.User;
import org.xmcc.jdbc.dao.BaseDao;

import java.sql.Connection;

/**
 * Created  by lusiwei on 2018/10/11
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {


    //查询用户名
    public User queryUserName(Connection connection, String username) {
        String sql = "select * from tab_user where username=?";
        User user = super.querySingle(connection, User.class, sql, username);
        System.out.println("---lsw---user值=" + user + "," + "当前类=UserDaoImpl.queryUserName()");
        return user;
    }
}
