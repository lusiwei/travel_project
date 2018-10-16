package com.lusiwei.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lusiwei.controller.BaseController;
import com.lusiwei.dao.SearchDao;
import com.lusiwei.dao.impl.SearchDaoImpl;
import com.lusiwei.dto.PageUtil;
import com.lusiwei.pojo.Route;
import com.lusiwei.service.SearchService;
import com.lusiwei.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/15
 * @author lusiwei
 */
public class SearchServiceImpl implements SearchService {
    private SearchDao searchDao=new SearchDaoImpl();
    private Connection connection=null;
    private String resultString=null;
    private PageUtil<Route> routePageUtil;
    @Override
    public String queryRoute(String keyword,int curPage) {
        try {
            connection= JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //获取page工具实例
        routePageUtil=new PageUtil<>();
        //设置limit
        int t1=(curPage-1)*routePageUtil.getPageSize();
        int t2=routePageUtil.getPageSize();
        //查询单页数据
        List<Route> routeList=searchDao.queryRoutes(connection,keyword,t1,t2);
        //查询数据条数
        long dataNumber=searchDao.queryCount(connection,keyword);
        //设置数据条数
        routePageUtil.setTotalCount(dataNumber);
        //设置routeList到pageUtil对象
        routePageUtil.setDataList(routeList);
        //设置当前页
        routePageUtil.setCurPage(curPage);
        try {
             resultString=BaseController.objectMapper.writeValueAsString(routePageUtil);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(resultString);
        JDBCUtils.close(connection,null);
        return resultString;
    }
}
