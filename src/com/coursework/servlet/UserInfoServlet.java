package com.coursework.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public UserInfoServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    HttpSession session = request.getSession();

    // ?????????, ????? ?? ???????????? ? ??????? (login) ??? ???.
    UserAccount loginedUser = MyUtils.getLoginedUser(session);

    // ???? ??? ?? ????? ? ??????? (login).
    if (loginedUser == null) {

    	// Redirect (?????????????) ? ???????? login.
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }

    Connection conn = MyUtils.getStoredConnection(request);

    String errorString = null;
    List<UserAccount> list = null;
    try {
      list = DBUtils.queryUser(conn);
    } catch (SQLException e) {
      e.printStackTrace();
      errorString = e.getMessage();
    }
    
    // ????????? ?????????? ? request attribute ????? ??? ??? forward (?????????????).
    request.setAttribute("errorString", errorString);
    request.setAttribute("userList", list);
    request.setAttribute("logginedUser", loginedUser);

 // ???? ???????????? ??? ????? ? ??????? (login), ?? forward (?????????????) ? ????????
    // /WEB-INF/views/userInfoView.jsp
    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
    dispatcher.forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    doGet(request, response);
  }

}