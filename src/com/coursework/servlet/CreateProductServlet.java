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

import com.coursework.beans.Product;
import com.coursework.utils.DBUtils;
import com.coursework.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/createProduct" })
public class CreateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateProductServlet() {
        super();
    }
 
    // ���������� �������� �������� ��������.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        dispatcher.forward(request, response);
    }
 
    // ����� ������������ ������ ���������� ��������, � �������� Submit.
    // ���� ����� ����� ������.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
        String name = (String) request.getParameter("name");
        String priceStr = (String) request.getParameter("price");
        String costStr = (String) request.getParameter("cost");
        float price = 0;
        float cost = 0;
        try {
            price = Float.parseFloat(priceStr);
            cost = Float.parseFloat(costStr);
        } catch (Exception e) {
        }
 
        String errorString = null;
 
        // ����� �������� �������� ������ [a-zA-Z_0-9]
        // ����� ������� 1 ������.
        String regex = "\\w+";
 
        if (code == null || !code.matches(regex)) {
            errorString = "Product Code invalid!";
        }
        Product product = null;
        if (errorString == null) {
            try {
            	if ((product = DBUtils.findProduct(conn, code)) == null) {
	            	product = new Product(code, name, price, cost);
	                DBUtils.insertProduct(conn, product);
            	}
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // ��������� ���������� � request attribute ����� ��� ��� forward � views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
 
        // ���� ������� ������ forward (���������������) � �������� 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        }
        // ���� ��� ������.
        // Redirect (�������������) � �������� �� ������� ���������.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
 
}