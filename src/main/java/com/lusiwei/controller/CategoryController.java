package com.lusiwei.controller;

import com.lusiwei.pojo.Category;
import com.lusiwei.service.CategoryService;
import com.lusiwei.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created  by lusiwei on 2018/10/12
 * @author lusiwei
 */

@WebServlet("/categoryController")
public class CategoryController extends BaseController{
    private CategoryService categoryService = new CategoryServiceImpl();
    public void initCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> categoryList=categoryService.queryCategoryList();
        response.getWriter().write(objectMapper.writeValueAsString(categoryList));
    }
}
