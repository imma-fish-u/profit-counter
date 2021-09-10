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

import com.coursework.beans.Product;
import com.coursework.beans.ProfitData;
import com.coursework.beans.SumProfitData;
import com.coursework.utils.DBUtils;
import com.coursework.utils.MyUtils;

@WebServlet(urlPatterns = { "/profitList" })
public class CountProfitServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CountProfitServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    Connection conn = MyUtils.getStoredConnection(request);
    String dateStr = (String) request.getParameter("date");
   
    String errorString = null;
    List<ProfitData> list = null;
    SumProfitData sum = null;
    try {
      list = DBUtils.queryProfitData(conn,dateStr);
      sum = DBUtils.sumUpAllProfitData(conn,dateStr);
      if (DBUtils.findProfitDate(conn, dateStr) == null)
      	DBUtils.insertProfitPureData(conn, sum);
    } catch (SQLException e) {
      e.printStackTrace();
      errorString = e.getMessage();
    }

    // Store info in request attribute, before forward to views
    request.setAttribute("errorString", errorString);
    request.setAttribute("dateStr", dateStr);
    request.setAttribute("profitList", list);
    request.setAttribute("profitSum", sum);

    // Forward to /WEB-INF/views/productListView.jsp
    RequestDispatcher dispatcher = request.getServletContext()
                                   .getRequestDispatcher("/WEB-INF/views/profitListView.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
      Connection conn = MyUtils.getStoredConnection(request);
      
      String dateStr = (String) request.getParameter("dateStr");
      String spendingsStr = (String) request.getParameter("spendings");
      String revenueStr = (String) request.getParameter("revenue");
      String marginProfitStr = (String) request.getParameter("marginProfit");
      float spendings = 0;
      float revenue = 0;
      float marginProfit = 0;
      try {
    	  spendings = Float.parseFloat(spendingsStr);
    	  revenue = Float.parseFloat(revenueStr);
    	  marginProfit = Float.parseFloat(marginProfitStr);
      } catch (Exception e) {
      }
      
      SumProfitData sum = null;
      String errorString = null;

      try {
    	  if ((sum = DBUtils.findProfitDate(conn, dateStr)) != null)
    	  {
    		  sum.setSumMarginProfit(marginProfit);
    		  sum.setSumPrice(revenue);
    		  sum.setSpendings(spendings);
    		  sum.setPureProfit();
    		  DBUtils.updateSumProfitTable(conn, sum);
    		  request.setAttribute("profitSum", sum);
    	  }
      } catch (SQLException e) {
          e.printStackTrace();
          errorString = e.getMessage();
      }
      // Сохранить информацию в request attribute перед тем как forward к views.
      request.setAttribute("errorString", errorString);
      

      // Если имеется ошибка, forward к странице edit.
      if (errorString != null) {
          RequestDispatcher dispatcher = request.getServletContext()
                  .getRequestDispatcher("/WEB-INF/views/profitListView.jsp");
          dispatcher.forward(request, response);
      }
      // Если все хорошо.
      // Redirect к странице со списком продуктов.
      else {
          response.sendRedirect(request.getContextPath() + "/profitReportList");
      }
  }

}
