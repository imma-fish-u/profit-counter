package com.coursework.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursework.beans.ProfitData;
import com.coursework.beans.SumProfitData;
import com.coursework.utils.DBUtils;
import com.coursework.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/createProfitDate" })
public class CreateProfitDateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateProfitDateServlet() {
        super();
    }
 
    // Отобразить страницу создания продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createProfitDateView.jsp");
        dispatcher.forward(request, response);
    }
 
    // Когда пользователь вводит информацию, и нажимает Submit.
    // Этот метод будет вызван.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String dateStr = (String) request.getParameter("dateReport");
        
        /*SumProfitData profitSum = null;
        try { 
            if ((profitSum = DBUtils.findProfitDate(conn, dateStr)) == null)
            {
            	profitSum = new SumProfitData(dateStr);
            	DBUtils.insertProfitDate(conn, dateStr);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } */
 
        // Сохранить информацию в request attribute перед тем как forward к views.
        
        request.setAttribute("date", dateStr);
        response.sendRedirect(request.getContextPath() + "/profitList?date=" + dateStr);

    }
 
}