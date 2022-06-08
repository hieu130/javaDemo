package com.example.java.controller;

import com.example.java.entity.CategoryStatus;
import com.example.java.model.CategoryModel;
import org.jboss.weld.environment.logging.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class DeleteCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        categoryModel = new CategoryModel();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        Category category = CategoryModel.findById(categoryId);
        category.setStatus(CategoryStatus.DELETED);
        category.setDeletedAt(LocalDateTime.now());

        if(categoryModel.update(categoryId, category)) {
            resp.sendRedirect("admin/category/list.jsp");
        }
    }
}
