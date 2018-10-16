package com.lusiwei.controller;

import com.lusiwei.pojo.User;
import com.lusiwei.service.RouteDetailService;
import com.lusiwei.service.impl.RouteDetailServiceImpl;
import com.lusiwei.util.CookieUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Created  by lusiwei on 2018/10/14
 *
 * @author lusiwei
 */
@WebServlet("/routeDetailController")
public class RouteDetailController extends BaseController {
    private RouteDetailService routeDetailService = new RouteDetailServiceImpl();
    private String resultString;
    private LinkedList<String> linkedList=new LinkedList<>();
    public void queryRouteDetailByRid(HttpServletRequest request, HttpServletResponse response) {
        String rid = request.getParameter("rid");
//        Cookie cookie=new Cookie("history",rid);
        Cookie historyCookie = CookieUtil.getCookieByName(request.getCookies(), "history");
        //如果获取到的cookie为空
        if (historyCookie == null) {
            Cookie cookie=new Cookie("history",rid);
            response.addCookie(cookie);
        }else {
            //cookie不为空
            //将cookie字符串分割成string数组
            String[] split = historyCookie.getValue().split("-");
            //将分割后的数组转换成list
            List<String> strings = Arrays.asList(split);
            //通过构造方法把list转换成LinkedLIst
            LinkedList<String> cookieLinkedList = new LinkedList<>(strings);
            //如果有重复,就移除重复的rid
            cookieLinkedList.remove(rid);
            //将新浏览的rid添加到cookieLinkedList
            cookieLinkedList.addFirst(rid);
            //通过String.join方法把linkedList 转换成以-连接的字符串
            String cookieString = String.join("-", cookieLinkedList);
            //将新(改变后)的cookieString放到cookie中
            Cookie cookie=new Cookie("history",cookieString);
            response.addCookie(cookie);
        }
        User user = (User) request.getSession().getAttribute("user");
        resultString = "查询失败";
        int uid;
        if (user != null) {
            uid = user.getUid();
        } else {
            uid = 0;
        }
        resultString= routeDetailService.queryRouteByRid(Integer.parseInt(rid), uid);
        try {
            response.getWriter().write(resultString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
