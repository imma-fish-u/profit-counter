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
 
@WebServlet(urlPatterns = { "/editUser" })
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditUserServlet() {
        super();
    }
 
    // ���������� �������� �������������� ��������.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        Integer id = Integer.parseInt(request.getParameter("id"));
 
        UserAccount user = null;
 
        String errorString = null;
 
        try {
            user = DBUtils.findUser(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // ������ �� �������.
        // User �� ���������� ��� �������������� (edit).
        if (errorString != null && user == null) {
            response.sendRedirect(request.getServletPath() + "/userInfo");
            return;
        }
 
        // ��������� ���������� � request attribute ����� ��� ��� forward � views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", user);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // ����� ����, ��� ������������ �������������� ���������� ����� � ����� �� ������ Submit.
    // ������ ����� ����� ��������.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        String id = (String) request.getParameter("Id");
  	    String userName = (String) request.getParameter("User_Name");
  	    String password = (String) request.getParameter("Password");
  	    String role = (String) request.getParameter("Role");
  	    String email = (String) request.getParameter("Email");
  	    String acceptedStr = (String) request.getParameter("Accepted"); 
        Boolean accepted = false;
        //Integer id = 0;
        try {
            accepted = Boolean.parseBoolean(acceptedStr);
            //id = Integer.parseInt(idStr);
        } catch (Exception e) {
        }
        UserAccount user = new UserAccount(); 
        user.setId(id);
        user.setUserName(userName);
	    user.setPassword(password);
	    user.setRole(role);
	    user.setEmail(email);
	    user.setAccepted(accepted);
	    
        String errorString = null;
 
        try {
            DBUtils.updateUser(conn, user);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // ��������� ���������� � request attribute ����� ��� ��� forward � views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", user);
 
        // ���� ������� ������, forward � �������� edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
            dispatcher.forward(request, response);
        }
        // ���� ��� ������.
        // Redirect � �������� �� ������� ���������.
        else {
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}