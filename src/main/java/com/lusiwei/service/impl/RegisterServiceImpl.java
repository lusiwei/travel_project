package com.lusiwei.service.impl;

import com.lusiwei.dao.impl.UserDaoImpl;
import com.lusiwei.pojo.User;
import com.lusiwei.service.RegisterService;
import com.lusiwei.util.JDBCUtils;
import com.lusiwei.util.MailUtils;
import com.lusiwei.util.UuidUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created  by lusiwei on 2018/10/11
 *
 * @author lusiwei
 */
public class RegisterServiceImpl implements RegisterService {
    private UserDaoImpl userDao = new UserDaoImpl();
    Connection connection = null;
    public boolean checkUserName(String username) {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = userDao.queryUserName(connection, username);
        JDBCUtils.close(connection,null);
        if (user == null) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email) {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.close(connection,null);
        return userDao.queryUserEmail(connection, email);
    }

    public void register(User user, HttpServletRequest request) {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String code = UuidUtil.getUuid();
        user.setStatus("N");
        user.setCode(code);
        request.getSession().setAttribute("code", code);

        int i = userDao.registerUpdate(connection, user);
        System.out.println("---lsw---code值=" + code + "," + "当前类=RegisterServiceImpl.register()");
        String emailText = "<a href='http://localhost:80/travel/registerController?method=activeUser&code=" + code + "'>点击激活</a>";
        if (i > 0) {
            MailUtils.sendMail("13232677506@163.com", emailText, "账户激活邮件");
        }
        JDBCUtils.close(connection,null);
    }

    public void updateStatus(String code) {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userDao.updateStatus(connection,code);
        JDBCUtils.close(connection,null);
    }

    @Override
    public User login(String username, String password) {
        try {
            connection=JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = userDao.login(connection, username, password);

        JDBCUtils.close(connection,null);
        return user;
    }
}
