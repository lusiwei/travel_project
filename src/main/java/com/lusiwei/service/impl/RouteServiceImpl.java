package com.lusiwei.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lusiwei.dao.RouteDao;
import com.lusiwei.dao.impl.RouteDaoImpl;
import com.lusiwei.dto.PageUtil;
import com.lusiwei.pojo.Route;
import com.lusiwei.service.RouteService;
import com.lusiwei.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/13
 * @author lusiwei
 */
public class RouteServiceImpl implements RouteService {
    private Connection connection=null;
    private RouteDao routeDao=new RouteDaoImpl();
    private ObjectMapper objectMapper=new ObjectMapper();
    private String routeListString;
    private PageUtil<Route> routePageUtil;


    /**
     *
     * @param cid
     * @param curPage
     * @return
     */
    @Override
    public String queryRouteByCid(Integer cid,int curPage,String recentRoute) {
        //获取数据库连接
        try {
            connection= JDBCUtils.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //获取page工具实例
        routePageUtil=new PageUtil<>();
        //设置limit数值
        int t1=(curPage-1)*routePageUtil.getPageSize();
        int t2=routePageUtil.getPageSize();
        //查询单页数据
        List<Route> routeList = routeDao.queryRouteByCid(connection, cid,t1,t2);
        //查询数据条数
        long dataNumber=routeDao.queryCount(connection,cid);
        //设置数据条数
        routePageUtil.setTotalCount(dataNumber);
        //设置routeList到pageUtil对象
        routePageUtil.setDataList(routeList);
        //设置当前页
        routePageUtil.setCurPage(curPage);
        //设置最近访问
        routePageUtil.setRecentRoute(recentRoute);
        try {
            routeListString=objectMapper.writeValueAsString(routePageUtil);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JDBCUtils.close(connection,null);
        return routeListString;
    }

    @Override
    public String queryRouteByRid(int rid) {
        try {
            connection=JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String rString=null;
        List<Route> routeList=routeDao.queryRouteByRid(connection,rid);
        try {
            rString  = objectMapper.writeValueAsString(routeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JDBCUtils.close(connection,null);
        return rString;
    }


}
