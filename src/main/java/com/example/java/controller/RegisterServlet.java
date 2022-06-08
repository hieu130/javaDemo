package com.example.java.controller;

import com.example.java.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegisterServlet extends HttpServlet {
    private AccountModel accountModel;

    public RegisterServlet() {
        this.accountModel = new MySqlAccountModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // lấy giá trị từ form gửi lên.
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String confirmPassword = req.getParameter("confirmPassword");
        String fullName = req.getParameter("fullName");
        // Khởi tạo đối tượng account từ thông tin truyền lên.
        Account account = Account.AccountBuilder.anAccount()
                .withUsername(username)
                .withEmail(email)
                .withPassword(password)
                .withConfirmPassword(confirmPassword)
                .withFullName(fullName)
                .build();

        if(!account.isValid()){
            // trả dữ liệu cũ về form
            req.setAttribute("account", account);
            // kèm theo thông tin lỗi
            req.setAttribute("errors", account.getErrors());
            // tất cả được trả về
            req.getRequestDispatcher("/user/register.jsp").forward(req, resp);
            return;
        }
        // thực hiện save
        accountModel.save(account);
        req.setAttribute("account", account);
        req.getRequestDispatcher("/client/users/login.jsp").forward(req, resp);
    }

    public static void main(String[] args) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("username", "Enter username");
        errors.put("password", "Enter password");
        errors.put("confirmPassword", " Enter nter confirm password");
        errors.put("fullName", "Enter fullName");
        if (errors.containsKey("username")) {
        }
    }
}
