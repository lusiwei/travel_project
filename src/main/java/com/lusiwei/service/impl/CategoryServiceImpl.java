package com.lusiwei.service.impl;

import com.lusiwei.dao.CategoryDao;
import com.lusiwei.dao.impl.CategoryDaoImpl;
import com.lusiwei.pojo.Category;
import com.lusiwei.service.CategoryService;
import com.lusiwei.util.Constant;
import com.lusiwei.util.JDBCUtils;
import com.lusiwei.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created  by lusiwei on 2018/10/12
 *
 * @author lusiwei
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private Connection connection = null;
    private List<Category> categoryList;

    /**
     *
     * @return List
     */
    @Override
    public List<Category> queryCategoryList() {
        categoryList = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> tuples = jedis.zrangeWithScores(Constant.CATEGORY_KEY, 0, -1);
        if (tuples != null && tuples.size() > 0) {
            System.out.println("从缓存取数据");
            for (Tuple tuple : tuples) {
                categoryList.add(new Category((int) tuple.getScore(), tuple.getElement()));
            }
        } else {
            System.out.println("从数据库取数据");
            categoryList = categoryDao.queryCategoryList(connection);
            for (int i = 0; i < categoryList.size(); i++) {
                jedis.zadd(Constant.CATEGORY_KEY, categoryList.get(i).getCid(), categoryList.get(i).getCname());
            }
        }
        JDBCUtils.close(connection, null);
        JedisUtil.close(jedis);
        return categoryList;
    }
}
