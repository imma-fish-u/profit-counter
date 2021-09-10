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
 
@WebServlet(urlPatterns = { "/createProfitLine" })
public class CreateProfitLineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateProfitLineServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = MyUtils.getStoredConnection(request);
        String dateStr = (String) request.getParameter("date");
        
        String errorString = null;
        List<Product> list = null;
        try {
          list = DBUtils.queryProduct(conn);
        } catch (SQLException e) {
          e.printStackTrace();
          errorString = e.getMessage();
        }

        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("dateStr", dateStr);
        request.setAttribute("productList", list);
    	
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createProfitLineView.jsp");
        dispatcher.forward(request, response);
    }
 
    // ����� ������������ ������ ����������, � �������� Submit.
    // ���� ����� ����� ������.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String dateStr = (String) request.getParameter("dateStr");
        String code = (String) request.getParameter("code");
        String quantityStr = (String) request.getParameter("quantity");
        Integer quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
            
        } catch (Exception e) {
        }
 
        String errorString = null;
        ProfitData profit = null;
        // ����� �������� �������� ������ [a-zA-Z_0-9]
        // ����� ������� 1 ������.
        String regex = "\\w+";
 
        if (code == null || !code.matches(regex)) {
            errorString = "Product Code invalid!";
        }
 
        if (errorString == null) {
            try {
                Product product = DBUtils.findProduct(conn, code);
                SumProfitData sumProfit = DBUtils.findProfitDate(conn, dateStr);
                profit = new ProfitData(sumProfit, product, quantity);
                DBUtils.insertProfitMarginalData(conn, profit);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // ��������� ���������� � request attribute ����� ��� ��� forward � views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("dateStr", dateStr);
        request.setAttribute("profitMargin", profit);
 
        // ���� ������� ������ forward (���������������) � �������� 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProfitLineView.jsp");
            dispatcher.forward(request, response);
        }
        // ���� ��� ������.
        // Redirect (�������������) � �������� �� ������� ���������.
        else {
            response.sendRedirect(request.getContextPath() + "/profitList?date=" + dateStr);
        }
    }
 
}
