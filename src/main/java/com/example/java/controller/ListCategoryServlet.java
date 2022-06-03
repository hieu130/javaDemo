package com.example.java.controller;

import com.example.java.model.CategoryModel;
import com.example.java.model.MySqlCategory;
import org.jboss.weld.environment.logging.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        categoryModel = new MySqlCategory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> productList = categoryModel.findByAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("").forward(req, resp);
    }
}
