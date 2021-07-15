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
import javax.servlet.http.HttpSession;

import com.coursework.beans.UserAccount;
import com.coursework.utils.DBUtils;
import com.coursework.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // �������� �������� Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward (�������������) � �������� /WEB-INF/views/loginView.jsp
        // (������������ �� ����� ����� �������� ������
        // � ��������� JSP ������������� � ����� WEB-INF).
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // ����� ������������ ������ userName & password, � �������� Submit.
    // ���� ����� ����� ��������.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "������� ��� ������������ � ������!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // ����� user � DB.
                user = DBUtils.findUser(conn, userName, password);
 
                if (user == null) {
                    hasError = true;
                    errorString = "������������ ������ ��� ��� ������������";
                } else if (user.getAccepted() == false) {
                    hasError = true;
                    errorString = "������ ������������ �� ����������� ���������������";
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // � ������, ���� ���� ������,
        // forward (�������������) � /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
 
            // ��������� ���������� � request attribute ����� forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward (�������������) � �������� /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        // � ������, ���� ��� ������.
        // ��������� ���������� ������������ � Session.
        // � ������������� � �������� userInfo.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
            // ���� ������������ �������� ������� "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // ��������, ������� Cookie
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            // Redirect (�������������) �� �������� /userInfo.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}