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
//        System.out.println("---lsw---user值=" + user + "," + "当前类=UserDaoImpl.queryUserName()");
        return user;
    }

    //查询邮箱
    public boolean queryUserEmail(Connection connection, String email) {
        String sql = "select * from tab_user where email=?";
        User user = super.querySingle(connection, User.class, sql, email);
        if (user == null) {
            return true;
        }
        return false;
    }

    public int registerUpdate(Connection connection, User user) {
        String sql = "INSERT INTO tab_user(username,PASSWORD,NAME,birthday,sex,telephone,email,STATUS,CODE) VALUES(?,?,?,?,?,?,?,?,?)";
        int update = super.update(connection, sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
        return update;
    }

    public void updateStatus(Connection connection, String code) {
        //更新激活状态
        String sql="update tab_user set status=? where code=?";
        super.update(connection, sql, "Y",code);
    }

    public User login(Connection connection, String username, String password) {
        String sql="select * from tab_user where username=? and password=?";
        User user = super.querySingle(connection, User.class,sql, username, password);
        return user;
    }
}
