package com.example.java.controller;

import com.example.java.model.CategoryModel;
import com.example.java.model.MySqlCategory;
import org.jboss.weld.environment.logging.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCategoryServlet {
    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        categoryModel = new MySqlCategory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category  = categoryModel.findById(Integer.parseInt(req.getParameter("id")));
        if(category == null) {
            category = new Category();
        }
        req.setAttribute("", category);
        req.getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
