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

import com.coursework.utils.DBUtils;
import com.coursework.utils.MyUtils;

@WebServlet(urlPatterns = { "/deleteProfit" })
public class DeleteProfitLineServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public DeleteProfitLineServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    Connection conn = MyUtils.getStoredConnection(request);

    String id = (String) request.getParameter("id");
    String dateStr = (String) request.getParameter("dateStr");
    String errorString = null;

    try {
      DBUtils.deleteProfit(conn, id);
    } catch (SQLException e) {
      e.printStackTrace();
      errorString = e.getMessage();
    }

    if (errorString != null) {
      request.setAttribute("errorString", errorString);
      request.setAttribute("dateStr", dateStr);
      RequestDispatcher dispatcher = request.getServletContext()
                                     .getRequestDispatcher("/WEB-INF/views/deleteProfitView.jsp");
      dispatcher.forward(request, response);
    }
    else {
      response.sendRedirect(request.getContextPath() + "/profitList?date="+dateStr);
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    doGet(request, response);
  }

}
