package com.coursework.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coursework.beans.Product;
import com.coursework.beans.ProfitData;
import com.coursework.beans.SumProfitData;
import com.coursework.beans.UserAccount;

public class DBUtils {

	//////////////User DBUtils//////////////
  public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {

    String sql = "Select * from User_Account "
                 + " where User_Name = ? and Password= ?";

    PreparedStatement pstm = conn.prepareStatement(sql);
    pstm.setString(1, userName);
    pstm.setString(2, password);
    ResultSet rs = pstm.executeQuery();

    if (rs.next()) {
      UserAccount user = new UserAccount();
      user.setUserName(userName);
      user.setPassword(password);
      String role = rs.getString("role");
      Boolean accepted = rs.getBoolean("accepted");
      user.setAccepted(accepted);
      user.setRole(role);
      return user;
    }
    return null;
  }

  public static UserAccount findUser(Connection conn, String userName) throws SQLException {

    String sql = "Select * from User_Account " + " where User_Name = ? ";

    PreparedStatement pstm = conn.prepareStatement(sql);
    pstm.setString(1, userName);

    ResultSet rs = pstm.executeQuery();

    if (rs.next()) {
      String password = rs.getString("Password");
      UserAccount user = new UserAccount();
      user.setUserName(userName);
      user.setPassword(password);
      return user;
    }
    return null;
  }
  
  public static UserAccount findUser(Connection conn, Integer id) throws SQLException {

	    String sql = "Select * from User_Account " + " where id = ? ";

	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setInt(1, id);

	    ResultSet rs = pstm.executeQuery();

	    if (rs.next()) {
	      UserAccount user = new UserAccount();
	      user.setId(Integer.toString(id));
	      return user;
	    }
	    return null;
	  }
  
  public static List<UserAccount> queryUser(Connection conn) throws SQLException {
	    String sql = "Select * from User_Account";

	    PreparedStatement pstm = conn.prepareStatement(sql);

	    ResultSet rs = pstm.executeQuery();
	    List<UserAccount> list = new ArrayList<UserAccount>();
	    while (rs.next()) {
	      String id = Integer.toString(rs.getInt("id"));	
	      String userName = rs.getString("User_Name");
	      String password = rs.getString("Password");
	      String role = rs.getString("Role");
	      String email = rs.getString("Email");
	      Boolean accepted = rs.getBoolean("Accepted"); 
	      UserAccount user = new UserAccount();
	      user.setId(id);
	      user.setUserName(userName);
	      user.setPassword(password);
	      user.setRole(role);
	      user.setEmail(email);
	      user.setAccepted(accepted);
	      list.add(user);
	    }
	    return list;
  }
  
  public static void insertUser(Connection conn, UserAccount user) throws SQLException {
	    String sql = "Insert into User_Account(User_Name, Password, role, Email, accepted) values (?,?,?,?,?)";

	    PreparedStatement pstm = conn.prepareStatement(sql);

	    pstm.setString(1, user.getUserName());
	    pstm.setString(2, user.getPassword());
	    pstm.setString(3, user.getRole());
	    pstm.setString(4, user.getEmail());
	    pstm.setBoolean(5, false);

	    pstm.executeUpdate();
	  }
  
  public static void updateUser(Connection conn, UserAccount user) throws SQLException {
	    String sql = "Update User_Account set User_Name=?, Password=?, role=?, email=?, Accepted=? where id=? ";

	    PreparedStatement pstm = conn.prepareStatement(sql);
	    
	    pstm.setString(1, user.getUserName());
	    pstm.setString(2, user.getPassword());
	    pstm.setString(3, user.getRole());
	    pstm.setString(4, user.getEmail());
	    pstm.setBoolean(5, user.getAccepted());
	    
	    pstm.setString(6, user.getId());
	    pstm.executeUpdate();
  }
  
  public static void deleteUser(Connection conn, Integer id) throws SQLException {
	    String sql = "Delete from user_account where id=? ";

	    PreparedStatement pstm = conn.prepareStatement(sql);

	    pstm.setInt(1, id);

	    pstm.executeUpdate();
  }
  ///////////////////////////////////////////////////

  
  //////////////Product DBUtils//////////////
  public static List<Product> queryProduct(Connection conn) throws SQLException {
    String sql = "Select * from Product ";

    PreparedStatement pstm = conn.prepareStatement(sql);

    ResultSet rs = pstm.executeQuery();
    List<Product> list = new ArrayList<Product>();
    while (rs.next()) {
      String code = rs.getString("Code");
      String name = rs.getString("Name");
      float price = rs.getFloat("Price");
      float cost = rs.getFloat("Cost");
      Product product = new Product();
      product.setCode(code);
      product.setName(name);
      product.setPrice(price);
      product.setCost(cost);
      list.add(product);
    }
    return list;
  }

  public static Product findProduct(Connection conn, String code) throws SQLException {
    String sql = "Select * from Product where Code=?";

    PreparedStatement pstm = conn.prepareStatement(sql);
    pstm.setString(1, code);

    ResultSet rs = pstm.executeQuery();

    while (rs.next()) {
      String name = rs.getString("Name");
      float price = rs.getFloat("Price");
      float cost = rs.getFloat("Cost");
      Product product = new Product(code, name, price, cost);
      return product;
    }
    return null;
  }

  public static void updateProduct(Connection conn, Product product) throws SQLException {
    String sql = "Update Product set Name =?, Price=?, Cost=? where Code=? ";

    PreparedStatement pstm = conn.prepareStatement(sql);

    pstm.setString(1, product.getName());
    pstm.setFloat(2, product.getPrice());
    pstm.setFloat(3, product.getCost());
    pstm.setString(4, product.getCode());
    pstm.executeUpdate();
  }

  public static void insertProduct(Connection conn, Product product) throws SQLException {
    String sql = "Insert into Product(Code, Name, Price, Cost) values (?,?,?,?)";

    PreparedStatement pstm = conn.prepareStatement(sql);

    pstm.setString(1, product.getCode());
    pstm.setString(2, product.getName());
    pstm.setFloat(3, product.getPrice());
    pstm.setFloat(4, product.getCost());

    pstm.executeUpdate();
  }

  public static void deleteProduct(Connection conn, String code) throws SQLException {
    String sql = "Delete from Product where Code= ?";

    PreparedStatement pstm = conn.prepareStatement(sql);

    pstm.setString(1, code);

    pstm.executeUpdate();
  }
	///////////////////////////////////////////////////
  
  public static SumProfitData sumUpAllProfitData(Connection conn, String date) throws SQLException {
	  String sql  = "SELECT SUM(quantity), SUM(price), SUM(cost), SUM(profit_margin) FROM Profit WHERE date_report = ?";
  
	  PreparedStatement pstm = conn.prepareStatement(sql);
	  pstm.setString(1, date);
	  
	  ResultSet rs = pstm.executeQuery();
	  SumProfitData sumProfit = new SumProfitData();
	  if (rs.next()) {
		  sumProfit.setSumQuantity(rs.getInt(1));
		  sumProfit.setSumPrice(rs.getFloat(2));
		  sumProfit.setSumCost(rs.getFloat(3));
		  sumProfit.setSumMarginProfit(rs.getFloat(4));
	  }
	  sumProfit.setDateReport(date);
	  return sumProfit;
  }
  
  public static void updateProfitTable(Connection conn, ProfitData profit) throws SQLException {
	  String sql  =  "UPDATE Profit SET price = ?, cost = ?, profit_margin = ? WHERE profit_id = ?";
  
	    PreparedStatement pstm = conn.prepareStatement(sql);

	    pstm.setFloat(1, profit.getPrice());
	    pstm.setFloat(2, profit.getCost());
	    pstm.setFloat(3, profit.getMarginProfit());
	    pstm.setInt(4, profit.getId());
	    pstm.executeUpdate();
  }
	
  public static List<ProfitData> queryProfitData(Connection conn, String date) throws SQLException {
	    String sql = "Select prod.*, prof.quantity, prof.profit_id from Product prod, Profit prof Where prod.code = prof.product_code And prof.date_report = ?";

	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, date);
	    
	    ResultSet rs = pstm.executeQuery();
	    List<ProfitData> list = new ArrayList<ProfitData>();
	    while (rs.next()) {
	      String code = rs.getString("Code");
	      Integer quantity = rs.getInt("Quantity");
	      Integer id = rs.getInt("profit_id");
	      Product product = findProduct(conn,code);
	      ProfitData profit = new ProfitData();
	      profit.setId(id);
	      profit.setProduct(product);
	      profit.setQuantity(quantity);
	      profit.setPrice();
	      profit.setCost();
	      profit.setMarginProfit();
	      updateProfitTable(conn, profit);
	      
	      list.add(profit);
	    }
	    return list;
	  }

  		//вставка для дальнейшего расчета валовой прибыли
	  public static void insertProfitMarginalData(Connection conn, ProfitData profit) throws SQLException {
	    String sql = "Insert into Profit(product_code, quantity, date_report) values (?,?,?)";

	    PreparedStatement pstm = conn.prepareStatement(sql);
	    Product product = profit.getProduct();
	    
	    pstm.setString(1, product.getCode());
	    pstm.setInt(2, profit.getQuantity());
	    pstm.setString(3, profit.getSumProfitData().getDateReport());

	    pstm.executeUpdate();
	  }
	  
	/////////////////////////////////////////////////  

	//////////////SumProfit DBUtils//////////////
	  
	  public static SumProfitData findProfitDate(Connection conn, String date) throws SQLException {
		    String sql = "Select * from profit_sum where date_id=?";

		    PreparedStatement pstm = conn.prepareStatement(sql);
		    pstm.setString(1, date);

		    ResultSet rs = pstm.executeQuery();
		    
		    while (rs.next()) {
		    	SumProfitData profit = new SumProfitData(date);
		    	profit.setDateReport(date);

		      return profit;
		    }
		    return null;
		  }
	  
	  public static List<SumProfitData> querySumProfitData(Connection conn) throws SQLException {
		    String sql = "Select * from profit_sum";

		    PreparedStatement pstm = conn.prepareStatement(sql);
		    
		    ResultSet rs = pstm.executeQuery();
		    List<SumProfitData> list = new ArrayList<SumProfitData>();
		    while (rs.next()) {
		      String date_id = rs.getString("date_id");
		      Float spendings =rs.getFloat("spendings");
		      Float sum_profit_margin =rs.getFloat("sum_profit_margin");
		     // Float profit_pure =rs.getFloat("profit_pure");
		      Float revenue = rs.getFloat("revenue");
		      SumProfitData profit = new SumProfitData(date_id);
    		  profit.setSumMarginProfit(sum_profit_margin);
    		  profit.setSumPrice(revenue);
    		  profit.setSpendings(spendings);
    		  profit.setPureProfit();
		      
		      list.add(profit);
		    }
		    return list;
		  }
	  
	  public static void insertProfitPureData(Connection conn, SumProfitData profit) throws SQLException {
		    String sql = "Insert into profit_sum(date_id, sum_profit_margin) values (?,?)";

		    PreparedStatement pstm = conn.prepareStatement(sql);
		    
		    //pstm.setFloat(1, profit.getSpendings());
		    pstm.setString(1, profit.getDateReport());
		    pstm.setFloat(2, profit.getSumMarginProfit());
		    
		    pstm.executeUpdate();
	  }
	  
	  public static void updateSumProfitTable(Connection conn, SumProfitData profit) throws SQLException {
		  String sql  =  "UPDATE profit_sum SET spendings = ?, sum_profit_margin = ?, profit_pure = ?, revenue = ? WHERE date_id = ?";
		  
		    PreparedStatement pstm = conn.prepareStatement(sql);

		    pstm.setFloat(1, profit.getSpendings());
		    pstm.setFloat(2, profit.getSumMarginProfit());
		    pstm.setFloat(3, profit.getPureProfit());
		    pstm.setFloat(4, profit.getSumPrice());
		    pstm.setString(5, profit.getDateReport());

		    pstm.executeUpdate();
	  }
	  
	  public static void deleteProfit(Connection conn, String id) throws SQLException {
		    String sql = "Delete from profit where profit_id= ?";

		    PreparedStatement pstm = conn.prepareStatement(sql);

		    pstm.setString(1, id);

		    pstm.executeUpdate();
		  }
}