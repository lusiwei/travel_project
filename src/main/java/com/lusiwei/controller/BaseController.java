package com.lusiwei.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created  by lusiwei on 2018/10/11
 * @author lusiwei
 */
public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        Class<? extends BaseController> aClass = this.getClass();
        methodName=methodName==null?"defaultMethod":methodName;
        try {
            Method method1 = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            String string= (String) method1.invoke(this, req, resp);
//            System.out.println("-----"+string);
            if (string != null) {
                if (string.contains("redirect")) {
                    resp.sendRedirect(string.substring(string.indexOf(":")+1));
                }else {
                    req.getRequestDispatcher(string);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
