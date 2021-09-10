package com.coursework.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursework.beans.SumProfitData;
import com.coursework.utils.DBUtils;
import com.coursework.utils.MyUtils;

@WebServlet(urlPatterns = { "/profitReportList" })
public class ProfitReportListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ProfitReportListServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    Connection conn = MyUtils.getStoredConnection(request);

    String errorString = null;
    List<SumProfitData> list = null;
    try {
      list = DBUtils.querySumProfitData(conn);
    } catch (SQLException e) {
      e.printStackTrace();
      errorString = e.getMessage();
    }

    List<Float> profitArr = new ArrayList<Float>();
    List<String> dateArr = new ArrayList<String>();
    for (int i = 0; i < list.size(); i++) {
    	profitArr.add(list.get(i).getPureProfit()); 
    	dateArr.add('"'+list.get(i).getDateReport()+'"');
    }
    // Store info in request attribute, before forward to views
    request.setAttribute("errorString", errorString);
    request.setAttribute("profitList", list);
    request.setAttribute("profitArr", profitArr);
    request.setAttribute("dateArr", dateArr);

    // Forward to /WEB-INF/views/productListView.jsp
    RequestDispatcher dispatcher = request.getServletContext()
                                   .getRequestDispatcher("/WEB-INF/views/profitReportListView.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    doGet(request, response);
  }

}
