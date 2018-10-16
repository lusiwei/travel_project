package com.lusiwei.controller;

import com.lusiwei.service.SearchService;
import com.lusiwei.service.impl.SearchServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created  by lusiwei on 2018/10/15
 *
 * @author lusiwei
 */
@WebServlet("/searchController")
public class SearchController extends BaseController {
    private SearchService searchService = new SearchServiceImpl();
    private String curPage;

    public void search(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("-------------");
        String keyword = request.getParameter("searchWord");
        System.out.println(keyword);
        //获取当前页
        curPage = request.getParameter("curPage");
        //当前页为空时,赋值为1
        curPage = curPage == null ? "1" : curPage;
        System.out.println(keyword);
        String searchList = searchService.queryRoute(keyword, Integer.parseInt(curPage));
        try {
            response.getWriter().write(searchList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
