package com.coursework.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursework.beans.UserAccount;
import com.coursework.utils.DBUtils;
import com.coursework.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public RegisterServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || email == null || userName.length() == 0 || password.length() == 0 || email.length() == 0 ) {
            hasError = true;
            errorString = "Введите имя, почту и пароль!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Найти user в DB.
                user = DBUtils.findUser(conn, userName);
 
                if (user != null) {
                    hasError = true;
                    errorString = "Пользователь с данным именем уже создан";
                } else {
                  Boolean accepted = false;
                  user = new UserAccount(userName, password, email, accepted);
                  DBUtils.insertUser(conn, user);
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/registerView.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
 
            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward (перенаправить) к странице /WEB-INF/views/registerView.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");
 
            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            // Redirect (Перенаправить) на страницу /login.
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
 
}
