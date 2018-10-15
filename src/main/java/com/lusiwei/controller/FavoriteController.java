package com.lusiwei.controller;

import com.lusiwei.dto.ResultMessageEnum;
import com.lusiwei.pojo.User;
import com.lusiwei.service.FavoriteService;
import com.lusiwei.service.impl.FavoriteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by lusiwei on 2018/10/14
 *
 * @author lusiwei
 */
@WebServlet("/favoriteController")
public class FavoriteController extends BaseController {
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private String resultMessage = ResultMessageEnum.FAVORITE_FAIL.toString();
    private boolean b = false;

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        //获取用户id
        Integer uid;
        if (user != null) {
            uid = user.getUid();
            try{
                b = favoriteService.addFavorite(Integer.parseInt(rid), uid);
            }catch(Exception e){
                b=false;
            }
        }
        if (b) {
            resultMessage = ResultMessageEnum.FAVORITE_SUCCESS.toString();
        }
        try {
            response.getWriter().write(resultMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void queryMyFavorite(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String resultString;
        if (user != null) {
            Integer uid = user.getUid();
            resultString = favoriteService.queryMyFavorite(uid);
        } else {
            resultString = ResultMessageEnum.FAVORITE_FAIL.toString();
        }
        try {
            response.getWriter().write(resultString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
