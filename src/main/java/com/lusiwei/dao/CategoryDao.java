package com.lusiwei.dao;

import com.lusiwei.pojo.Category;

import java.sql.Connection;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/12
 * @author lusiwei
 */
public interface CategoryDao {
    List<Category> queryCategoryList(Connection connection);
}
