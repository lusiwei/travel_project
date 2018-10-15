package com.lusiwei.dao.impl;

import com.lusiwei.dao.CategoryDao;
import com.lusiwei.pojo.Category;
import org.xmcc.jdbc.dao.BaseDao;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/12
 * @author lusiwei
 */
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao{

    @Override
    public List<Category> queryCategoryList(Connection connection) {
        String sql="select * from tab_category order by cid";
        List<Category> categoryList = super.queryList(connection, Category.class, sql);
        return categoryList;
    }

}
