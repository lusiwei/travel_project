package com.lusiwei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created  by lusiwei on 2018/10/11
 *
 * @author lusiwei
 */
public class BaseController extends HttpServlet {
    public static ObjectMapper objectMapper = new ObjectMapper();
    private String methodName;
    private static final String REDIRECT = "redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        methodName = req.getParameter("method");
        Class<? extends BaseController> aClass = this.getClass();
        methodName = methodName == null ? "defaultMethod" : methodName;
        String methodReturn;
        try {
            Method method1 = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            methodReturn = (String) method1.invoke(this, req, resp);
            if (methodReturn != null) {
                if (methodReturn.contains(REDIRECT)) {
                    resp.sendRedirect(methodReturn.substring(methodReturn.indexOf(":") + 1));
                } else {
                    req.getRequestDispatcher(methodReturn);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
