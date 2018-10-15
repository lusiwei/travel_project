package com.lusiwei.controller;

import com.lusiwei.pojo.User;
import com.lusiwei.service.RouteDetailService;
import com.lusiwei.service.impl.RouteDetailServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by lusiwei on 2018/10/14
 *
 * @author lusiwei
 */
@WebServlet("/routeDetailController")
public class RouteDetailController extends BaseController {
    private RouteDetailService routeDetailService = new RouteDetailServiceImpl();
    private String string;

    public void queryRouteDetailByRid(HttpServletRequest request, HttpServletResponse response) {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        string = "查询失败";
        int uid;
        if (user != null) {
            uid = user.getUid();
            string = routeDetailService.queryRouteByRid(Integer.parseInt(rid), uid);
        }
        try {
            response.getWriter().write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
