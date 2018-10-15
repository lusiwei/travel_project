package com.lusiwei.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lusiwei.dto.ResultMessageEnum;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by lusiwei on 2018/10/11
 * @author lusiwei
 */
@WebServlet("/checkCodeController")
public class CheckCodeController extends BaseController {
    public String checkCode(HttpServletRequest request, HttpServletResponse response) {
        //获取Ajax发送过来的验证码
        String code = request.getParameter("code");
        System.out.println("---lsw---code值=" + code + "," + "当前类=CheckCodeController.checkCode()");
        ObjectMapper objectMapper = new ObjectMapper();
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        String resultMessage;
        try {
            if (checkcode_server.equalsIgnoreCase(code)) {
                resultMessage=ResultMessageEnum.CHECKCODE_TRUE.toString();
            } else {
                resultMessage=ResultMessageEnum.CHECKCODE_FALSE.toString();
            }
            response.getWriter().write(resultMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
