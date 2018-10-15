package com.lusiwei.controller;

import com.lusiwei.service.RouteService;
import com.lusiwei.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by lusiwei on 2018/10/13
 *
 * @author lusiwei
 * 接收前台点击导航条时发过来的请求
 */
@WebServlet("/routeController")
public class RouteController extends BaseController {
    private RouteService routeService = new RouteServiceImpl();

    public void queryRouteByCid(HttpServletRequest request, HttpServletResponse response) {
        //获取cid
        String cid = request.getParameter("cid");
        //获取当前页
        String curPage = request.getParameter("curPage");
        //当前页为空时,赋值为1
        curPage = curPage == null ? "1" : curPage;
        //cid为空时赋值为1
        cid = (cid == null) ? "1" : cid;
        //从业务层拿到routeList的json字符串
        String routeList = routeService.queryRouteByCid(Integer.parseInt(cid), Integer.parseInt(curPage));
        //返回到前台
        try {
            response.getWriter().write(routeList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
