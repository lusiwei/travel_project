package com.lusiwei.controller;

import com.lusiwei.dto.ResultMessageEnum;
import com.lusiwei.pojo.User;
import com.lusiwei.service.impl.RegisterServiceImpl;
import com.lusiwei.util.Constant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by lusiwei on 2018/10/12
 *
 * @author lusiwei
 */
@WebServlet("/loginController")
public class LoginController extends BaseController {
    RegisterServiceImpl registerService = new RegisterServiceImpl();
    String resultMessage=null;
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("--------login-----");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean b = registerService.checkUserName(username);
        User user;
        if (b) {
            resultMessage = ResultMessageEnum.USERNAME_ERROR.toString();
        } else {
            user = registerService.login(username, password);
            if (user != null) {
                if (Constant.STATUS_ENABLE.equalsIgnoreCase(user.getStatus())) {
                    request.getSession().setAttribute("user", user);
                    resultMessage=ResultMessageEnum.PASSWORD_TRUE.toString();
                }else{
                    resultMessage=ResultMessageEnum.STATU_FAIL.toString();
                }
//                return "index.html";
            } else {
                resultMessage = ResultMessageEnum.PASSWORD_ERROR.toString();
            }
        }
        try {
            response.getWriter().write(resultMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "redirect:login.html";
    }

    public void initUser(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            response.getWriter().write(objectMapper.writeValueAsString(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:login.html";
    }
}
