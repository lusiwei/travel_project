package com.lusiwei.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lusiwei.dto.ResultMessageEnum;
import com.lusiwei.pojo.User;
import com.lusiwei.service.impl.RegisterServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created  by lusiwei on 2018/10/11
 *
 * @author lusiwei
 */

@WebServlet("/registerController")
public class RegisterController extends BaseController {
    RegisterServiceImpl registerService = new RegisterServiceImpl();

    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String username = request.getParameter("username");
        boolean flag = registerService.checkUserName(username);
        ObjectMapper objectMapper = new ObjectMapper();
        String returnMessage;
        if (flag) {
            returnMessage = ResultMessageEnum.USERNAME_AVAILABLE.toString();
        } else {
            returnMessage = ResultMessageEnum.USERNAME_UNAVAILABLE.toString();
        }
        try {
            response.getWriter().write(returnMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String checkEmail(HttpServletRequest request, HttpServletResponse response) {
        //获取邮箱
        String email = request.getParameter("email");
        boolean flag = registerService.checkEmail(email);
        ObjectMapper objectMapper = new ObjectMapper();
        String returnMessage;
        try {
            if (flag) {
                returnMessage = ResultMessageEnum.EMAIL_AVAILABLE.toString();
            } else {
                returnMessage = ResultMessageEnum.EMAIL_UNAVAILABLE.toString();
            }
            response.getWriter().write(returnMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String register(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        //获取所有提交
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(user);
        System.out.println("string值=" + string);
        registerService.register(user,request);
        return "redirect:register_ok.html";
    }

    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("激活用户");
        String code = request.getParameter("code");
        String code2=(String) request.getSession().getAttribute("code");
        if (code.equals(code2)) {
            registerService.updateStatus(code);
            response.getWriter().write("<script>alert('激活成功');location.href='http://localhost:80/travel/login.html'</script>");
        }else {
            response.getWriter().write("<script>alert('激活失败');location.href='http://localhost:80/travel/register.html'</script>");
        }
    }
}
