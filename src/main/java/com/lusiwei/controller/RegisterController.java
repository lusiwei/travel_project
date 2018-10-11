package com.lusiwei.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lusiwei.dto.ResultMessage;
import com.lusiwei.service.impl.RegisterServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by lusiwei on 2018/10/11
 * @author lusiwei
 */

@WebServlet("/registerController")
public class RegisterController extends BaseController {
    RegisterServiceImpl registerService = new RegisterServiceImpl();

    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        System.out.println("开始校验");
        String username = request.getParameter("username");
        boolean flag = registerService.checkUserName(username);
        ObjectMapper objectMapper = new ObjectMapper();
        String returnMessage;
        if (flag) {
            returnMessage = objectMapper.writeValueAsString(new ResultMessage(true, "用户名不可用"));
        } else {
            returnMessage = objectMapper.writeValueAsString(new ResultMessage(false, "用户名可用"));
        }
        System.out.println("---lsw---returnMessage值=" + returnMessage + "," + "当前类=RegisterController.checkUserName()");
        try {
            response.getWriter().write(returnMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
